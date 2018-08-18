# ohTips

This library has the aim to help you creating simple UI dialogs for the most common usage:

* **"Show tip of the day"** dialog
* a simple dialog with a single "ok" button
* **Rate this app** dialog

...and more is coming!

![screenshot](https://raw.githubusercontent.com/Mapk26/ohTips/master/ohtips-screens.png)

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
## ohDialog class
With this class you can create simple dialogs to show a single message. ATM only one "ok" button (text customizable) is available and it just closes the dialog. Soon I will add a second button so that you can set negative and positive buttons like in standard android dialogs.

### How to use ohDialog
```java
    private OhDialog ohDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ohDialog = new OhDialog(this);
	ohDialog.setIcon(R.drawable.ic_speaker_notes);
        ohDialog.setTitle("Simple Dialog");
        ohDialog.setMessage("This is a simple dialog created with ohDialog class!");
        ohDialog.setThemeColor(R.color.green_400);

        ohDialog.show();
    }
```

## ohTips class
This is the class to show tips of the day, it comes with a checkbox "Don't show tips of the day".

One tip per day is shown, btw there is a method ```.restart()```to force the dialog to be shown again.

### How to use ohTips
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
        tips.setButtonText("Nice!");

        tips.show();
    }
```


## ohStars class
This is the "Rate this app" dialog that you can use to ask users to review your app on the marketplace. 

The default values are: 10 launches and 7 days.
It means that the dialog will be shown to the user after 10 launches of the app or 7 days after installation (first app launch).

A ```.showNow()``` method will trigger the dialog immediatly.

### How to use ohStars
```java
    private OhStars ohStars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ohStars = new OhStars(this);

        ohStars.setLaunches(8); // custom number of launches
        ohStars.setDays(5);	// custom number of days
	ohStars.setThemeColor(R.color.blue_400);
        ohStars.show();
    }
    
    public void showRate(View view) {
        ohStars.showNow();
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
