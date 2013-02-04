package com.bindroid.sample;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;

import com.bindroid.BindingMode;
import com.bindroid.converters.AdapterConverter;
import com.bindroid.converters.BoolConverter;
import com.bindroid.converters.ToStringConverter;
import com.bindroid.ui.EditTextTextProperty;
import com.bindroid.ui.UiBinder;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ViewModel model = new ViewModel();

    UiBinder.bind(new EditTextTextProperty((EditText) this.findViewById(R.id.TextField)), model,
        "StringValue", BindingMode.TwoWay);
    UiBinder.bind(this, R.id.TextView, "Text", model, "StringValue", BindingMode.OneWay);
    UiBinder.bind(this, R.id.ListView, "Adapter", model, "Dates", BindingMode.OneWay,
        new AdapterConverter(DateView.class));

    UiBinder.bind(this, R.id.CountTextView, "Text", model, "Count", BindingMode.OneWay,
        new ToStringConverter("Count: %d"));
    UiBinder.bind(this, R.id.TextLengthView, "Text", model, "TextLength", BindingMode.OneWay,
        new ToStringConverter("Text length: %d"));
    UiBinder.bind(this, R.id.SumView, "Text", model, "CountPlusTextLength", BindingMode.OneWay,
        new ToStringConverter("Sum: %d"));
    UiBinder.bind(this, R.id.EvenSpinner, "Visibility", model, "CountIsEven", BindingMode.OneWay,
        new BoolConverter());
    UiBinder.bind(this, R.id.OddSpinner, "Visibility", model, "CountIsEven", BindingMode.OneWay,
        new BoolConverter(true));
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }

}
