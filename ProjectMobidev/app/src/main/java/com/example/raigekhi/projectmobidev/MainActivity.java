package com.example.raigekhi.projectmobidev;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName,editSection,editMajor,editAddress,editId;
    Button buttonAdddata;
    Button btnview;
    Button btnUpdate;
    Button btnDelete;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = (EditText)findViewById(R.id.editTextStudentName);
        editSection = (EditText)findViewById(R.id.editTextSection);
        editMajor = (EditText)findViewById(R.id.editTextMajor);
        editAddress = (EditText)findViewById(R.id.editTextAddress);
        buttonAdddata =(Button)findViewById(R.id.buttonAdd);
        editId = (EditText)findViewById(R.id.editTextupdate);
        btnview = (Button)findViewById(R.id.buttonview);
        btnSearch =(Button)findViewById(R.id.buttonsearch);
        btnUpdate= (Button)findViewById(R.id.btnupdate);
        btnDelete= (Button)findViewById(R.id.buttondelete);

        addData();
        viewAll();
        UpdateData();
        DeleteData();
        searchData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void searchData() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             boolean isSearch = myDb.searchdata(editId.getText().toString());
                                             if (isSearch)
                                                 Toast.makeText(MainActivity.this, "Data found", Toast.LENGTH_LONG).show();

                                             else
                                                 Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_LONG).show();

                                         }
                                     }
        );
    }

    public void addData() {
        buttonAdddata.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(editName.getText().toString(), editSection.getText().toString(), editMajor.getText().toString(), editAddress.getText().toString());
                        if (isInserted)
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_LONG).show();

                    }
                }
        );
    }







    public void viewAll() {
        btnview.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0) {

                            showMessage("Error","No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("ID :"+ res.getString(0)+"\n");
                            buffer.append("StudentName :"+ res.getString(1)+"\n");
                            buffer.append("Section :"+ res.getString(2)+"\n");
                            buffer.append("Major :"+ res.getString(3)+"\n");
                            buffer.append("Address :"+ res.getString(4)+"\n");


                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }


    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void UpdateData() {
        btnUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdated  =  myDb.updateData(editId.getText().toString(),editName.getText().toString(),editSection.getText().toString(),editMajor.getText().toString(),editAddress.getText().toString());
                        if(isUpdated)
                            Toast.makeText(MainActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
