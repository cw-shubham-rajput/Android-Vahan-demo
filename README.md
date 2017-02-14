# Android-Vahan-demo
A sample android app to get vehicle details from vahan

App sends a text message to vaahan no and starts a service for 10 minutes. there is broadcast receiver inside 
service which reacts when sms arrives from vaahan. After receiving a message app displays messsage body in form 
of popup even if app is closed. Both the receiver and service destroys themselves after 10 minutes.
