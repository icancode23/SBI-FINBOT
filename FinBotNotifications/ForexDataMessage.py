import requests
import json
head={'project_id': 'finbot-2ad8f', 'Content-Type': 'application/json', 'Authorization': 'key=AAAA7CQoP28:APA91bGLUFoa-alAOK7DDA3lnQwL1yKwChX8WulEZSgmxsfPBb4o0L-CybLLgt2J1jQ2I8FI4uuv1k7IALTPtZzYc68DZz7pjmYxxmu6_qKRbDeJj38oX7rU7qiPln8wGGKge1dclnyg'}
# messagestring=raw_input("Enter the message you wish to send as notification:")
d={'to': "cfrIlEtRAJ4:APA91bEU5jOkL9EPpukloYF2sSlAVZ9tse-SH4wUWV8vvGNfSXPJOx8s5uyiqNohYxcyDZabLYqrhWUYjP_avAS-1NHiQVPCuNMIRwLr6wB8h9m7juMfGTe8TbrNxsmXzAXvloPWbRX_"
, 'priority': 10,
	"data" : {
      "Message" : "Going to US...get your dollars ",
      "image"	: "http://i.imgur.com/PYadJwt.png"
      
      
    }}
lucky=requests.post(url='https://fcm.googleapis.com/fcm/send',headers=head,data=json.dumps(d))
print lucky.text
