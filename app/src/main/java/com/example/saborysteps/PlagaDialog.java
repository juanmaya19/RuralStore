package com.example.saborysteps;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;

public class PlagaDialog {

    public static void mostrarEnlace(final Context context, final String enlace) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Información de la Plaga");
        builder.setMessage("¿Deseas abrir este enlace en el navegador?");

        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                abrirEnlaceEnNavegador(context, enlace);
            }
        });

        builder.setNegativeButton("No", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private static void abrirEnlaceEnNavegador(Context context, String enlace) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(enlace));
        context.startActivity(intent);
    }
}

