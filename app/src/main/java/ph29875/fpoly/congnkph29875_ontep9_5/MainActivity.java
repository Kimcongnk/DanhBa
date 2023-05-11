package ph29875.fpoly.congnkph29875_ontep9_5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private FloatingActionButton floatingActionButton;
private RecyclerView recyclerView;
private UserAdapter userAdapter;
private ArrayList<DanhBa> danhBaArrayList = new ArrayList<>();
private Dao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dao = new Dao(MainActivity.this);


        floatingActionButton = findViewById(R.id.floatingActionButton);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialogEdit = new Dialog(MainActivity.this);
                dialogEdit.setContentView(R.layout.them_danhba);
                EditText edTen = dialogEdit.findViewById(R.id.editTextText);
                EditText edSdt = dialogEdit.findViewById(R.id.editTextText2);
                EditText edEmail = dialogEdit.findViewById(R.id.editTextText3);
                EditText edGhiChu = dialogEdit.findViewById(R.id.editTextText4);



                Button btnOk = dialogEdit.findViewById(R.id.button3);
                Button btnCancle = dialogEdit.findViewById(R.id.button4);
                btnCancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialogEdit.dismiss();
                    }
                });
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DanhBa newObj = new DanhBa();

                        newObj.setHoTen(edTen.getText().toString());
                        newObj.setSoDt(edSdt.getText().toString());
                        newObj.seteMail(edEmail.getText().toString());
                        newObj.setGhiChu(edGhiChu.getText().toString());
                        if (edTen.getText().toString().isEmpty() && edSdt.getText().toString().isEmpty() &&  edEmail.getText().toString().isEmpty() && edGhiChu.getText().toString().isEmpty()) {
                            Toast.makeText(getApplicationContext(),
                                    "Vui lòng nhập nội dung và thời gian", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (dao.isDuplicate(newObj)) {
                            Toast.makeText(getApplicationContext(),
                                    "Lớp học mã sinh này đã tồn tại", Toast.LENGTH_LONG).show();
                            return;
                        }
                        if (dao.insertFood(newObj) >0) {
                            Snackbar snackbar = Snackbar.make(view, "Thêm thành công", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                            onResume();
                            dialogEdit.dismiss();
                        } else
                            Toast.makeText(getApplicationContext(), "update không thành công", Toast.LENGTH_LONG).show();

                        dialogEdit.dismiss();
                    }
                });
                dialogEdit.show();

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        danhBaArrayList = dao.getAllData();
        userAdapter = new UserAdapter(danhBaArrayList);
        recyclerView.setAdapter(userAdapter);
    }
}