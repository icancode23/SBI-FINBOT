import requests
import json
head={'project_id': 'finbot-2ad8f', 'Content-Type': 'application/json', 'Authorization': 'key=AAAA7CQoP28:APA91bGLUFoa-alAOK7DDA3lnQwL1yKwChX8WulEZSgmxsfPBb4o0L-CybLLgt2J1jQ2I8FI4uuv1k7IALTPtZzYc68DZz7pjmYxxmu6_qKRbDeJj38oX7rU7qiPln8wGGKge1dclnyg'}
# messagestring=raw_input("Enter the message you wish to send as notification:")
d={'notification': {'sound': 'default', 'body': 'You have withdrawn Rs 5000',"title":"FinBot",'picture':'http://api.androidhive.info/images/minion.jpg',}, 'to': "cipc_dfrfls:APA91bGJctu_Xfbs_69dENfaj8kWagPMGVlqM8knSQpg-XXd0bpklEnnlEbDptifR95wnj3iQau6PLt5XtYJLurgmoZtCT8iNYCRu4RukZYCfvZXtMpIKEFCIQ-_f2OeLxeDIU7iTkfP"
, 'priority': 10,}
lucky=requests.post(url='https://fcm.googleapis.com/fcm/send',headers=head,data=json.dumps(d))
print lucky.text
d={'notification': {'sound': 'default', 'body': 'Rs 5000 have been added to your wallet for expense management',"title":"FinBot",'picture':'http://api.androidhive.info/images/minion.jpg',}, 'to': "cipc_dfrfls:APA91bGJctu_Xfbs_69dENfaj8kWagPMGVlqM8knSQpg-XXd0bpklEnnlEbDptifR95wnj3iQau6PLt5XtYJLurgmoZtCT8iNYCRu4RukZYCfvZXtMpIKEFCIQ-_f2OeLxeDIU7iTkfP"
, 'priority': 10,}
lucky=requests.post(url='https://fcm.googleapis.com/fcm/send',headers=head,data=json.dumps(d))
print lucky.text