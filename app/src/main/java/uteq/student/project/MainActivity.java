package uteq.student.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //activar menu lateral
        final DrawerLayout drawerLayout= findViewById(R.id.drawerLayout);
        findViewById(R.id.imageMenu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        findViewById(R.id.imageexit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this,"Cerrar sesion", Toast.LENGTH_SHORT).show();
                startActivity( new Intent(MainActivity.this, StartActivity.class));
            }
        });
        NavigationView navigationView= findViewById(R.id.navigationView);
        navigationView.setItemIconTintList(null);

        //abrir fragmentos
        NavController navController= Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        //Nombre de la pagina actual
        final TextView textView = findViewById(R.id.textTitle);
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                textView.setText(destination.getLabel());
            }
        });


    }


  /* cerrar sesion public void BtnCerrarSesion(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this,"Cerrar sesion", Toast.LENGTH_SHORT).show();
        startActivity( new Intent(MainActivity.this, StartActivity.class));
    }*/
}