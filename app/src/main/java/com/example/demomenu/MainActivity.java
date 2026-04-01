package com.example.demomenu;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView txtvHello, txtContext;
    Button btnAlertDialog, btnCustomDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        txtvHello = findViewById(R.id.txtvHello);
        txtContext = findViewById(R.id.txtContext);
        btnAlertDialog = findViewById(R.id.btnAlertDialog);
        btnCustomDialog = findViewById(R.id.btnCustomDialog);

        // Popup Menu
        txtvHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu();
            }
        });

        // Context Menu: đăng ký cho TextView này
        registerForContextMenu(txtContext);

        // AlertDialog
        btnAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        // Custom Dialog
        btnCustomDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDialog();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // =========================
    // Popup Menu
    // =========================
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, txtvHello);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.mnuOne) {
                    Toast.makeText(MainActivity.this, "Bạn chọn One", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.mnuTwo) {
                    Toast.makeText(MainActivity.this, "Bạn chọn Two", Toast.LENGTH_SHORT).show();
                } else if (id == R.id.mnuThree) {
                    Toast.makeText(MainActivity.this, "Bạn chọn Three", Toast.LENGTH_SHORT).show();
                }

                return true;
            }
        });

        popupMenu.show();
    }

    // =========================
    // Context Menu
    // =========================
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.txtContext) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
            menu.setHeaderTitle("Chọn chức năng");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuEdit) {
            Toast.makeText(this, "Bạn chọn Sửa", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mnuDelete) {
            Toast.makeText(this, "Bạn chọn Xóa", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mnuShare) {
            Toast.makeText(this, "Bạn chọn Chia sẻ", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onContextItemSelected(item);
    }

    // =========================
    // AlertDialog
    // =========================
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn thoát ứng dụng không?");

        builder.setPositiveButton("Có", (dialogInterface, i) -> {
            Toast.makeText(MainActivity.this, "Bạn chọn Có", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Không", (dialogInterface, i) -> {
            Toast.makeText(MainActivity.this, "Bạn chọn Không", Toast.LENGTH_SHORT).show();
        });

        builder.setNeutralButton("Hủy", (dialogInterface, i) -> {
            Toast.makeText(MainActivity.this, "Bạn chọn Hủy", Toast.LENGTH_SHORT).show();
        });

        builder.show();
    }

    // =========================
    // Custom Dialog
    // =========================
    private void showCustomDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);

        EditText edtName = dialog.findViewById(R.id.edtName);
        EditText edtEmail = dialog.findViewById(R.id.edtEmail);
        Button btnCloseDialog = dialog.findViewById(R.id.btnCloseDialog);

        btnCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();

                Toast.makeText(MainActivity.this,
                        "Tên: " + name + " - Email: " + email,
                        Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        });

        dialog.show();
    }

    // =========================
    // Options Menu
    // =========================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnuAdd) {
            Toast.makeText(this, "Bạn click vào Thêm mới", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mnuSearch) {
            Toast.makeText(this, "Bạn click vào Tìm kiếm", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mnuLogin) {
            Toast.makeText(this, "Bạn click vào Login", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mnuLogOut) {
            Toast.makeText(this, "Bạn click vào LogOut", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mnuFeedBack) {
            Toast.makeText(this, "Bạn click vào FeedBack", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.mnuHelp) {
            Toast.makeText(this, "Bạn click vào Help", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}