# SimpleHttp

http client library for android , built on top of **okhttp** library 

# Why
I designed this library to simplify the process of sending http and request , **and** to design an easy way to get the response without worrying about threads
 
<br><br>

# Installation

first edit `build.gradle` and add this line `maven { url 'https://jitpack.io' }`
    
    ...
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
    ...
    
and add this line in your dependencies in `app/build.gradle`

    implementation 'com.github.alicompiler:simplehttp:1.0.0'
    
    
    
<br><br>
    
#### How To Use



    new SimpleHttp().url(YOUR_URL)
                    .onComplete(response -> {
                        //UI THREAD
                        textView.setText(response.asString());
                    })
                    .sendReqeustAsync();
                    

<br><br>
###### SimpleHttp
<br>

method | params | purpose
--------------- | --------- | -------------
url | `String` url |set the url 
method | `HttpActionType` method | set the method type for your request , there is only `HttpActionType.GET` and `HttpActionType.POST` options
parameter | `String` key , `Object` value | add parameter to your request
header | `String` header , `String` value | add header to your request
attach | `File` file , `String` name | add file to upload with your request , this will take effect only when the method is set to `HttpActionType.POST`
attach | `File` file , `String` name , `String` mediaType | add file to upload with your request , this will take effect only when the method is set to `HttpActionType.POST`
onComplete | `OnComplete` onComplete | set a listener to be triggered when your request complete , this will only take effect your send your request calling `sendRequestAsync` method , when this listener is triggered it will be run on the **UI Thread**
onError | `OnError` onError | set a listener to be triggered when your request fail , this will only take effect your send your request calling `sendRequestAsync` method , when this listener is triggered it will be run on the **UI Thread**
sendRequest | - | send the request and return `SimpleHttpResponse` or thorws `IOException` , you can't call this method on **UI Thread**
sendRequestAsync | - | send the request on other thread and trigger **onComplete** listener when the request is completes, or trigger **onError** listener when the request fails



<br><br>
###### SimpleHttpResponse
<br>


method | purpose
--------------- | -------------
asString | return the response as String or null
asJsonObject | return the response as `JSONObject` , if the response can't be converted to `JSONObject` this method will return null
asJsonArray | return the response as `JSONArray` , if the response can't be converted to `JSONArray` this method will return null

<br><br>
###### OnComplete
<br>

method | parameter | purpose
--------------- | ------------- | ---------
onComplete | `SimpleHttpResponse` response | will be called when the resquest complete

<br><br>
###### OnError
<br>

method | parameter | purpose
--------------- | ------------- | ---------
onComplete | `IOException` exception | will be called when the resquest fail


<br><br>
## Examples
<br>

-- send post request with parameters and headers and setup onComplete & onError listeners

    new SimpleHttp().url(YOUR_URL)
                    .method(HttpActionType.POST)
                    .parameter("id" , 2)
                    .header("API_SECRET" , "SOME_KEY")
                    .onComplete(response -> {/* ON COMPETE */})
                    .onError(exception -> {/* ON COMPETE */})
                    .sendReqeustAsync();
                    

-- upload file

    new SimpleHttp().url(YOUR_URL)
                    .method(HttpActionType.POST)
                    .parameter("some_key" , "some_value")
                    .attach(file , "image")
                    .onComplete(response -> {/* ON COMPETE */})
                    .onError(exception -> {/* ON COMPETE */})
                    .sendReqeustAsync();

