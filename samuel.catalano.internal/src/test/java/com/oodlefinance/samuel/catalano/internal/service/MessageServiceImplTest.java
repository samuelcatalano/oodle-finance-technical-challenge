package com.oodlefinance.samuel.catalano.internal.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityNotFoundException;

import com.oodlefinance.samuel.catalano.internal.dto.MessageDTO;
import com.oodlefinance.samuel.catalano.internal.entity.Message;
import com.oodlefinance.samuel.catalano.internal.exception.ServiceException;
import com.oodlefinance.samuel.catalano.internal.repository.MessageRepository;
import com.oodlefinance.samuel.catalano.internal.service.impl.MessageServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

  @Mock
  private MessageRepository repository;

  @Mock
  private ModelMapper modelMapper;

  @InjectMocks
  private MessageServiceImpl messageService;

  @Test
  void testCreate_ShouldCreateMessage() throws ServiceException {
    final MessageDTO messageDTO = MessageDTO.builder().message("Created message").build();
    final Message message = new Message();

    when(modelMapper.map(messageDTO, Message.class)).thenReturn(message);
    when(repository.save(message)).thenReturn(message);
    when(modelMapper.map(message, MessageDTO.class)).thenReturn(messageDTO);

    final MessageDTO result = messageService.create(messageDTO);

    verify(repository, times(1)).save(message);
    verify(modelMapper, times(1)).map(message, MessageDTO.class);
    assertEquals(messageDTO, result);
  }

  @Test
  void testCreate_ShouldThrowServiceException() {
    final MessageDTO dto = MessageDTO.builder().message("Created message").build();
    final Message entity = new Message();

    when(modelMapper.map(eq(dto), eq(Message.class))).thenReturn(entity);
    when(repository.save(eq(entity))).thenThrow(new RuntimeException());

    assertThatExceptionOfType(ServiceException.class)
        .isThrownBy(() -> messageService.create(dto))
        .withMessage("Error persisting a new Message");

    verify(modelMapper, times(1)).map(eq(dto), eq(Message.class));
    verify(repository, times(1)).save(eq(entity));
    verify(modelMapper, never()).map(eq(entity), eq(MessageDTO.class));
  }

  @Test
  void testUpdate_ShouldThrowEntityNotFoundException() {
    // given
    Long id = 1L;
    MessageDTO dto = new MessageDTO();
    dto.setMessage("Test message");

    // when
    when(repository.findById(id)).thenReturn(Optional.empty());

    // then
    assertThrows(EntityNotFoundException.class, () -> messageService.update(id, dto));
    verify(repository).findById(id);
    verifyNoMoreInteractions(repository, modelMapper);
  }

  @Test
  void testFindById_ShouldReturnMessage() throws EntityNotFoundException {
    final Message message = Message.builder().message("Created message").build();
    final MessageDTO messageDTO = new MessageDTO();
    final Long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.of(message));
    when(modelMapper.map(message, MessageDTO.class)).thenReturn(messageDTO);

    final MessageDTO result = messageService.findById(id);
    assertEquals(messageDTO, result);
  }

  @Test
  void testFindAll_WhenRepositoryFindAllMethodReturnsData() throws ServiceException {
    List<Message> messages = Arrays.asList(
        new Message("message 1"),
        new Message("message 2"),
        new Message("message 3")
    );

    when(repository.findAll()).thenReturn(messages);

    final MessageDTO messageDTO1 = new MessageDTO();
    messageDTO1.setMessage("message 1");

    final MessageDTO messageDTO2 = new MessageDTO();
    messageDTO2.setMessage("message 2");

    final MessageDTO messageDTO3 = new MessageDTO();
    messageDTO3.setMessage("message 3");

    when(modelMapper.map(messages.get(0), MessageDTO.class)).thenReturn(messageDTO1);
    when(modelMapper.map(messages.get(1), MessageDTO.class)).thenReturn(messageDTO2);
    when(modelMapper.map(messages.get(2), MessageDTO.class)).thenReturn(messageDTO3);

    final List<MessageDTO> result = messageService.findAll();

    assertThat(result).hasSize(3);
    assertThat(result.get(0)).isEqualToComparingFieldByField(messageDTO1);
    assertThat(result.get(1)).isEqualToComparingFieldByField(messageDTO2);
    assertThat(result.get(2)).isEqualToComparingFieldByField(messageDTO3);

    verify(repository).findAll();
    verify(modelMapper, times(3)).map(any(Message.class), eq(MessageDTO.class));
  }

  @Test
  void findAll_WhenRepositoryFindAllMethodThrowsException() {
    when(repository.findAll()).thenThrow(new EntityNotFoundException("There are no messages to be retrieved"));

    assertThatExceptionOfType(ServiceException.class)
        .isThrownBy(() -> messageService.findAll())
        .withMessage("Error retrieving all existing messages")
        .withCauseInstanceOf(RuntimeException.class);
    verify(repository).findAll();
    verify(modelMapper, never()).map(any(Message.class), eq(MessageDTO.class));
  }

  @Test
  void testDeleteById_ShouldDeleteMessage() throws EntityNotFoundException, DataIntegrityViolationException {
    final Long id = 1L;
    when(repository.findById(eq(id))).thenReturn(Optional.of(new Message()));

    messageService.deleteById(id);

    verify(repository, times(1)).findById(eq(id));
    verify(repository, times(1)).deleteById(eq(id));
  }

  @Test
  void testDeleteById_ShouldThrowException() {
    Long id = 1L;
    when(repository.findById(eq(id))).thenReturn(Optional.of(new Message()));
    doThrow(DataIntegrityViolationException.class).when(repository).deleteById(eq(id));

    assertThatExceptionOfType(DataIntegrityViolationException.class)
        .isThrownBy(() -> messageService.deleteById(id));

    verify(repository, times(1)).findById(eq(id));
    verify(repository, times(1)).deleteById(eq(id));
  }
}
