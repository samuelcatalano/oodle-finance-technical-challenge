# Oodle Finance
Technical Challenge for Oodle Finance

### APIs:
> Post a new message (example)
* **POST**: http://localhost:8081/api/message/
> JSON Body:
```javascript
{"message": "Type any message to include"}
```
> Return all messages:
* **GET**: http://localhost:8081/api/message/all

> Return a message by id:
* **GET**: http://localhost:8081/api/message/555

> Delete a message by id:
* **DELETE**: http://localhost:8081/api/message/123

> Consul (Hashicorp) is configured:
* Consul runs on default port and once agent started successfully, browse http://localhost:8500/ui and you should see a console screen like –

![Image description](https://i.imgur.com/ZRJ09ui.png)

> Using IntelliJ IDEA it's possible runs both service at same time:

![Image description](https://i.imgur.com/IdIrCjl.png)
![Image description](https://i.imgur.com/DcNWgcl.png)
