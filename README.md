# ohTips

This library has the aim to help you creating a simple **"Show tip of the day"** dialog in few easy steps.
As any other similar dialog it comes with a -do not show- checkbox to disallow the tips on startup.

**It's a work in progress project, use it carefully until a stable release will be announced**

![screenshot](https://raw.githubusercontent.com/Mapk26/ohTips/master/screens/ohtips-screens.png)

## Import with Gradle
Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
		...
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```
dependencies {
	  implementation 'com.github.Mapk26:ohTips:1.1.0'
}
```

## How to use
It's really simple and basic to use. First of all add in your strings.xml 3 string-array for title, message and icon.
The icon string-array is not mandatory, if you skip it an info default icon will be used.

```xml
    <string-array name="ohtips_title">
        <item>Did you know?</item>
        <item>Did you know?</item>
        <item>It\' free!</item>
        <item>Donate</item>
    </string-array>


    <string-array name="ohtips_message">
        <item>You can use ohTips also for simple dialogs</item>
        <item>You can configure: top bar color, icon, title, content and button text</item>
        <item>This is distributed with MIT License, it means you can use this library for free in all your projects.</item>
        <item>You can offer me a beer to support this project!</item>
    </string-array>

    <!-- not mandatory, if you don't use your set of icons then a default info icon will be used -->
    <string-array name="ohtips_icon">
        <item>ic_info</item>
        <item>ic_palette</item>
        <item>ic_info</item>
        <item>ic_fastfood</item>
    </string-array>
```

Then create an instance of the BaseTip class, you can set the main color and button text. 

```java
    private OhTips tips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tips = new OhTips(this);
        tips.setThemeColor(R.color.blue_400);
        tips.setButtonText("Thanks!");

        tips.show();
    }
```

## License
```
MIT License

Copyright 2018 - MARCO NICOTRA (Mapk26)

Permission is hereby granted, free of charge, to any person 
obtaining a copy of this software and associated documentation 
files (the "Software"), to deal in the Software without restriction, 
including without limitation the rights to use, copy, modify, merge, 
publish, distribute, sublicense, and/or sell copies of the Software, 
and to permit persons to whom the Software is furnished to do so, 
subject to the following conditions:

The above copyright notice and this permission notice shall be included 
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, 
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES 
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, 
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
