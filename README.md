Got to Google Developer Console

Find Project id from url
Ex- https://console.developers.google.com/apis/credentials?project=api-project-119235637721311

1) Use in Code for CommonUtilities.SENDER_ID
CommonUtilities.SENDER_ID = 119235637721311

2) Then register app in api keys with sha1 and package name 

3) Google Cloud Messaging API [ON this]

Note : use for finding sha[Keytool is command found in java jre/bin]

>keytool -list -v -keystore c:\Users\test\.android\debug.keystore -alias androiddebugkey -storepass android -keypass android

Send GCM Message By
>http://mygo-niksworld.rhcloud.com/webserver/gcm/gcmserverpage.php?&regID=APA91bFVxfHHasdahzkrvr52LIVvacH7kmHFwdFf-oiflD5lC0b_P_loqWnz_Zrk40WFww27x94mOfUixAeyZoRP8ydTncCy1URnI0xgDBjucENZd1WdFwobVDuVQ-1DTj&msg=Hi
