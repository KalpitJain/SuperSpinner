# SuperSpinner
Material Design TextInputLayout with Spinner

### Include

    dependencies {
      compile 'com.creotix:superspinner:1.0.0'
    }


### Layout XML

    <com.creotix.superspinner.SuperSpinner
      android:id="@+id/super_spinner"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content" />

### Set it up

    SuperSpinner superSpinner = (SuperSpinner) findViewById(R.id.super_spinner);
    superSpinner.init("Hint Text");
    superSpinner.addAll(spinnerItems);


And you have a `Materialized Spinner` in your app

If you have a suggestion/improvement let me know! Found a bug? Report now!
