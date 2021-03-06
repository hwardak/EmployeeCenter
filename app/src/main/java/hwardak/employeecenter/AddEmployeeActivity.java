package hwardak.employeecenter;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class AddEmployeeActivity extends AppCompatActivity {

    DBDataAccess dbDataAccess;
//    int REQUEST_IMAGE_CAPTURE;

    private EditText editText_id;
    private EditText editText_first_name;
    private EditText editText_last_name;
    private EditText editText_middle_name;
    private EditText editText_date_of_birth;
    private EditText editText_sin_number;
    private EditText editText_email;
    private EditText editText_phone_number;
    private EditText editText_street_address;
    private EditText editText_city;
    private EditText editText_province;
    private EditText editText_postal_code;
    private EditText editText_starting_date;

    private ImageView imageView_photo;

    private Button button_take_photo;
    private Button button_save;

    ArrayList<EditText> list_edittexts;
    ArrayList<String> list_edittexts_values;

    /*
     * Stores the absolute path of the user's picture.
     * TO BE: stored internally and private.
     * Will be saved to DB and recalled when needed, both full-size and thumbnail.
     */
    String imagePath;
    private Uri photoURI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_form);

        dbDataAccess = new DBDataAccess(this);


        instantiateViews();
        populateEditTextListView();


        prepareFormAddVersion();

    }

    /*
     * activity_employee_form is modified here to fit the privileges this activity.
     *
     * button_save, and button_take_photo have their VISIBILITY  set to GONE by default. They are
     * set to VISIBLE here.
     */
    private void prepareFormAddVersion() {
        button_save.setVisibility(View.VISIBLE);
        button_take_photo.setVisibility(View.VISIBLE);
    }




    private void instantiateViews() {
        editText_id = (EditText) findViewById(R.id.edittext_id);
        editText_first_name = (EditText) findViewById(R.id.edittext_first_name);
        editText_last_name = (EditText) findViewById(R.id.edittext_last_name);
        editText_middle_name = (EditText) findViewById(R.id.edittext_middle_name);
        editText_date_of_birth = (EditText) findViewById(R.id.edittext_date_of_birth);
        editText_sin_number = (EditText) findViewById(R.id.edittext_sin_number);
        editText_email = (EditText) findViewById(R.id.edittext_email);
        editText_phone_number = (EditText) findViewById(R.id.edittext_phone_number);
        editText_street_address = (EditText) findViewById(R.id.edittext_street_address);
        editText_city = (EditText) findViewById(R.id.edittext_city);
        editText_province = (EditText) findViewById(R.id.edittext_province);
        editText_postal_code = (EditText) findViewById(R.id.edittext_postal_code);
        editText_starting_date = (EditText) findViewById(R.id.edittext_starting_date);

        button_take_photo = (Button) findViewById(R.id.button_take_photo);
        button_save = (Button) findViewById(R.id.button_save);

        imageView_photo = (ImageView) findViewById(R.id.photoImage);


    }

    private void populateEditTextListView() {
        list_edittexts = new ArrayList<>();
        list_edittexts_values = new ArrayList<>();

        list_edittexts.add(editText_id);
        list_edittexts.add(editText_first_name);
        list_edittexts.add(editText_middle_name);
        list_edittexts.add(editText_last_name);
        list_edittexts.add(editText_date_of_birth);
        list_edittexts.add(editText_sin_number);
        list_edittexts.add(editText_email);
        list_edittexts.add(editText_phone_number);
        list_edittexts.add(editText_street_address);
        list_edittexts.add(editText_city);
        list_edittexts.add(editText_province);
        list_edittexts.add(editText_postal_code);
        list_edittexts.add(editText_starting_date);
    }

    public void addEmployeeSaveButtonOnClick(View view) {

        if (doFormFieldsHaveEntries()) {
            if (isIdValid()) {
                if (isDateValid()) {
                    if (isEmailValid()) {
                        saveEmployee();
                        Toast.makeText(this, "Employee Saved Successfully", Toast.LENGTH_LONG).show();
                        this.onBackPressed();


                    }
                }
            }
        }
    }

    private boolean isIdValid() {
        boolean pass = true;

        if (dbDataAccess.doesIdExist(editText_id.getText().toString().trim())) {
            pass = false;
            editText_id.setError("ID is taken, choose another...");
        }

        return pass;
    }

    private void saveEmployee() {

        //Collect values from all EditText fields, and add them to String ArrayList.
        for (int i = 0; i < list_edittexts.size(); i++) {
            list_edittexts_values.add(list_edittexts.get(i).getText().toString());
        }

        //Save the absolute path for the profile picture taken.
        list_edittexts_values.add(imagePath);


        dbDataAccess.addEmployeeToTable(list_edittexts_values);
        list_edittexts_values.clear();
    }


    private boolean isEmailValid() {
        boolean pass = true;

        try {
            InternetAddress emailAddress = new InternetAddress(editText_email.getText().toString());
            emailAddress.validate();
        } catch (AddressException ex) {
            editText_email.setError("Invalid email...");
            pass = false;
        }
        return pass;
    }

    private boolean isDateValid() {
        boolean pass = true;

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setLenient(false);
        try {
            Date date = formatter.parse(editText_date_of_birth.getText().toString());
        } catch (ParseException e) {
            editText_date_of_birth.setError("Invalid date format... (dd/mm/yyyy)");
            pass = false;
        }

        try {
            Date date = formatter.parse(editText_starting_date.getText().toString());
        } catch (ParseException e) {
            editText_starting_date.setError("Invalid date format... (dd/mm/yyyy)");
            pass = false;
        }

        return pass;
    }

    private boolean doFormFieldsHaveEntries() {
        boolean pass = true;

        for (int i = 0; i < list_edittexts.size(); i++) {
            if (TextUtils.isEmpty(list_edittexts.get(i).getText().toString().trim())) {
                list_edittexts.get(i).setError("Entry Needed");
                pass = false;
            }
        }


        return pass;
    }


    /**
     * When 'Take Photo' button is pressed.
     *
     * @param view
     */
    public void takePhotoButtonOnClick(View view) {
        dispatchTakePictureIntent();
    }


    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
                Log.d("LOGTAG", " PHOTOFILE CREATED");
                Log.d("LOGTAG", photoFile.getAbsolutePath());
            } catch (IOException ex) {
                Log.d("LOGTAG", " PHOTOFILE NOT CREATED");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(this,
                        "hwardak.employeecenter.FileProvider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");
            //Log.d("widthBitmap", imageBitmap.getWidth() + "");
            //imageView_photo.setImageBitmap(imageBitmap);

            //super.onActivityResult(requestCode,resultCode, data);
            //this.getContentResolver().notifyChange(photoURI, null);
            Bitmap myBitmap = null;
            try {
                myBitmap = android.provider.MediaStore.Images.Media.getBitmap(getContentResolver(), photoURI);
                //create thumbnail
                Bitmap thumbnail = Bitmap.createScaledBitmap(myBitmap, myBitmap.getHeight()/6, myBitmap.getHeight()/6,false);
                Log.d("mybitmap", myBitmap.getHeight() + "");
                imageView_photo.setImageBitmap(thumbnail);
                //imageView_photo.setScaleType(ImageView.ScaleType.CENTER_CROP);


                // save the image to internal system
                if(myBitmap != null) {
                    try {
                        String name = editText_id.getText().toString().trim() + "_photo_.jpeg";
                        FileOutputStream fos = openFileOutput(name, MODE_PRIVATE);
                        //imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        myBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                        ///////comment in if you want the internal ( not cached ) absolute path to profile pic
                        //imagePath = getFileStreamPath(name).getAbsolutePath();
                        if (fos != null)
                            fos.close();
                        Log.d("bitmapHeight", myBitmap.getHeight()+"");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            } catch (Exception e) {
                Log.d("failedToLoad", "Failed to load", e);
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String imageFileName = editText_id.getText().toString().trim() + "_photo_";
        Log.d("imageFileName", imageFileName);
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpeg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        //NOTE THIS IS A TEMPORARY PATH to the Cached file
        imagePath = image.getAbsolutePath();
        Log.d("LOGTAG ", imagePath);

        return image;
    }


}
