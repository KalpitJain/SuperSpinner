# SuperSpinner
Material Design TextInputLayout with Spinner

### Include

    dependencies {
      compile 'com.android.support:appcompat-v7:23.1.1'
    }


### Layout XML

    <com.creotix.superspinner.SuperSpinner
      android:id="@+id/super_spinner"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content" />

### Set it up

    SuperSpinner superSpinner = (SuperSpinner) findViewById(R.id.super_spinner);
    superSpinner.init("Select Item Label");
    superSpinner.addAll(spinnerItems);


And you have a `Materialized Spinner` in your app

If you have a suggestion/improvement let me know! Found a bug? Report now!
