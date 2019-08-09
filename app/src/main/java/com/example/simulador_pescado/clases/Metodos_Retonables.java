package com.example.simulador_pescado.clases;

import android.text.format.Time;
import android.view.View;
import android.widget.Button;

import com.example.simulador_pescado.R;

public class Metodos_Retonables {
    //Devuelve la fecha actual en formato dia/mes/año
    public String Obtener_fecha(){
        Time today= new Time(Time.getCurrentTimezone());
        today.setToNow();
        int dia= today.monthDay;
        int mes= today.month;
        int año= today.year;
        mes++;
        String fecha= ""+dia+"/"+mes+"/"+año;
     return fecha;
    }
    //Devuelve si la tina esta asiganda o no
    public Boolean Tina_asignada(String tina){
        Boolean respuesta=false;
        switch (tina){

            case "tina 1":
                if (Utilidades.T_1_asignada){
                    respuesta=true;
                }
                break;

            case "tina 2":
                if (Utilidades.T_2_asignada){
                    respuesta=true;
                }

                break;
            case "tina 3":
                if (Utilidades.T_3_asignada){
                    respuesta=true;
                }

                break;
            case "tina 4":
                if (Utilidades.T_4_asignada){
                    respuesta=true;
                }
                break;

            case "tina 5":
                if (Utilidades.T_5_asignada){
                    respuesta=true;
                }
                break;

            case "tina 6":

                if (Utilidades.T_6_asignada){
                    respuesta=true;
                }
                break;

            case "tina 7":
                if (Utilidades.T_7_asignada){
                    respuesta=true;
                }
                break;

            case "tina 8":
                if (Utilidades.T_8_asignada){
                    respuesta=true;
                }
                break;

            case "tina 9":
                if (Utilidades.T_9_asignada){
                    respuesta=true;
                }
                break;

            case "tina 10":
                if (Utilidades.T_10_asignada){
                    respuesta=true;
                }
                break;

            case "tina 11":
                if (Utilidades.T_11_asignada){
                    respuesta=true;
                }

                break;
            case "tina 12":
                if (Utilidades.T_12_asignada){
                    respuesta=true;
                }
//    ---------------------------------------------empleados----------------------------------------
                break;
            case "empleado 1":
                if (Utilidades.emple_1_asignado){
                    respuesta=true;
                }
                break;

            case "empleado 2":
                if (Utilidades.emple_2_asignado){
                    respuesta=true;
                }

                break;
            case "empleado 3":
                if (Utilidades.emple_3_asignado){
                    respuesta=true;
                }

                break;
            case "empleado 4":
                if (Utilidades.emple_4_asignado){
                    respuesta=true;
                }
                break;

            case "empleado 5":
                if (Utilidades.emple_5_asignado){
                    respuesta=true;
                }
                break;

            case "empleado 6":

                if (Utilidades.emple_6_asignado){
                    respuesta=true;
                }
                break;

            case "empleado 7":
                if (Utilidades.emple_7_asignado){
                    respuesta=true;
                }
                break;

            case "empleado 8":
                if (Utilidades.emple_8_asignado){
                    respuesta=true;
                }
                break;

            case "empleado 9":
                if (Utilidades.emple_9_asignado){
                    respuesta=true;
                }
                break;

            case "empleado 10":
                if (Utilidades.emple_1_asignado){
                    respuesta=true;
                }
                break;
//-----------------------------------montacargas----------------------------------------------------
            case "montacargas 1":
                if (Utilidades.montacargas_1_asignada){
                    respuesta=true;
                }
                break;

            case "montacargas 2":
                if (Utilidades.montacargas_2_asignada){
                    respuesta=true;
                }

                break;
            case "montacargas 3":
                if (Utilidades.montacargas_3_asignada){
                    respuesta=true;
                }

                break;
            case "montacargas 4":
                if (Utilidades.montacargas_4_asignada){
                    respuesta=true;
                }
                break;
        }
        return respuesta;
    }
    // Devuelve si un empleado tiene una tina asignada

    //


    //libera Tina
    //este no es un metodo retornable pero esta aqui para quitar codigo del fragment de tinas
    public void liberar(String tina){
        switch (tina){
            case "tina 1":
                Utilidades.T_1_asignada=false;
                break;

            case "tina 2":
                Utilidades.T_2_asignada=false;

                break;
            case "tina 3":
                Utilidades.T_3_asignada=false;

                break;
            case "tina 4":
                Utilidades.T_4_asignada=false;
                break;

            case "tina 5":
                Utilidades.T_5_asignada=false;
                break;

            case "tina 6":

                Utilidades.T_6_asignada=false;
                break;

            case "tina 7":
                Utilidades.T_7_asignada=false;
                break;

            case "tina 8":
                Utilidades.T_8_asignada=false;
                break;

            case "tina 9":
                Utilidades.T_9_asignada=false;
                break;

            case "tina 10":
                Utilidades.T_10_asignada=false;
                break;

            case "tina 11":
                Utilidades.T_11_asignada=false;

                break;
            case "tina 12":
                Utilidades.T_12_asignada=false;

                break;

    //-------------------------------------------------empleados------------------------------------
            case "empleado 1":
                Utilidades.emple_1_asignado=false;
                break;

            case "empleado 2":
                Utilidades.emple_2_asignado=false;

                break;
            case "empleado 3":
                Utilidades.emple_3_asignado=false;

                break;
            case "empleado 4":
                Utilidades.emple_4_asignado=false;
                break;

            case "empleado 5":
                Utilidades.emple_5_asignado=false;
                break;

            case "empleado 6":

                Utilidades.emple_6_asignado=false;
                break;

            case "empleado 7":
                Utilidades.emple_7_asignado=false;
                break;

            case "empleado 8":
                Utilidades.emple_8_asignado=false;
                break;

            case "empleado 9":
                Utilidades.emple_9_asignado=false;
                break;

            case "empleado 10":
                Utilidades.emple_10_asignado=false;
                break;
//-----------------------------------montacargas----------------------------------------------------
            case "montacargas 1":
                Utilidades.montacargas_1_asignada=false;
                break;

            case "montacargas 2":
                Utilidades.montacargas_2_asignada=false;
                break;

            case "montacargas 3":
                Utilidades.montacargas_3_asignada=false;
                break;

            case "montacargas 4":
                Utilidades.montacargas_4_asignada=false;
                break;


        }

    }
    public  void resetear_botones(Button botones[],View view){

    }
    public void resetear_niveles(){
        Utilidades.nivel_1="Posision 1 libre";
        Utilidades.nivel_2="Posision 2 libre";
        Utilidades.nivel_3="Posision 3 libre";
        Utilidades.nivel_4="Posision 4 libre";
        Utilidades.nivel_5="Posision 5 libre";
    }
    public void niveles(String valor){
        int nivel_ocupado=niveles_ocupados(valor);
        switch (nivel_ocupado){
            case 0:
                break;
            case 1:
                Utilidades.nivel_1="Posision 1 ocupada";
                break;
            case 2:
                Utilidades.nivel_1="Posision 1 ocupada";
                Utilidades.nivel_2="Posision 2 ocupada";
                break;
            case 3:
                Utilidades.nivel_1="Posision 1 ocupada";
                Utilidades.nivel_2="Posision 2 ocupada";
                Utilidades.nivel_3="Posision 3 ocupada";
                break;
            case 4:
                Utilidades.nivel_1="Posision 1 ocupada";
                Utilidades.nivel_2="Posision 2 ocupada";
                Utilidades.nivel_3="Posision 3 ocupada";
                Utilidades.nivel_4="Posision 4 ocupada";
                break;

            case 5:
                Utilidades.nivel_1="Posision 1 ocupada";
                Utilidades.nivel_2="Posision 2 ocupada";
                Utilidades.nivel_3="Posision 3 ocupada";
                Utilidades.nivel_4="Posision 4 ocupada";
                Utilidades.nivel_5="Posision 5 ocupada";
                break;



        }
    }
    private int niveles_ocupados(String valor){
        int aux = 0;

        switch (valor){
            case "ninguno":
                break;
            case "estiba 1":
                aux=Utilidades.posisiones_ocupadas_estiba_1;

                break;
            case "estiba 2":
                aux=Utilidades.posisiones_ocupadas_estiba_2;

                break;
            case "estiba 3":
                aux=Utilidades.posisiones_ocupadas_estiba_3;

                break;
            case "estiba 4":
                aux=Utilidades.posisiones_ocupadas_estiba_4;

                break;
            case "estiba 5":
                aux=Utilidades.posisiones_ocupadas_estiba_5;

                break;
            case "estiba 6":
                aux=Utilidades.posisiones_ocupadas_estiba_6;

                break;
            case "estiba 7":
                aux=Utilidades.posisiones_ocupadas_estiba_7;

                break;
            case "estiba 8":
                aux=Utilidades.posisiones_ocupadas_estiba_8;

                break;
            case "estiba 9":
                aux=Utilidades.posisiones_ocupadas_estiba_9;

                break;
            case "estiba 10":
                aux=Utilidades.posisiones_ocupadas_estiba_10;
                break;

            case "estiba 11":
                aux=Utilidades.posisiones_ocupadas_estiba_11;
                break;


            case "estiba 12":
                aux=Utilidades.posisiones_ocupadas_estiba_12;

                break;
            case "estiba 13":
                aux=Utilidades.posisiones_ocupadas_estiba_13;

                break;
            case "estiba 14":
                aux=Utilidades.posisiones_ocupadas_estiba_14;

                break;
            case "estiba 15":
                aux=Utilidades.posisiones_ocupadas_estiba_15;

                break;
            case "estiba 16":
                aux=Utilidades.posisiones_ocupadas_estiba_16;

                break;
            case "estiba 17":
                aux=Utilidades.posisiones_ocupadas_estiba_17;

                break;
            case "estiba 18":
                aux=Utilidades.posisiones_ocupadas_estiba_18;

                break;
            case "estiba 19":
                aux=Utilidades.posisiones_ocupadas_estiba_19;

                break;
            case "estiba 20":
                aux=Utilidades.posisiones_ocupadas_estiba_20;

                break;
            case "estiba 21":
                aux=Utilidades.posisiones_ocupadas_estiba_21;

                break;
            case "estiba 22":
                aux=Utilidades.posisiones_ocupadas_estiba_22;

                break;
            case "estiba 23":
                aux=Utilidades.posisiones_ocupadas_estiba_23;

                break;
            case "estiba 24":
                aux=Utilidades.posisiones_ocupadas_estiba_24;

                break;
            case "estiba 25":
                aux=Utilidades.posisiones_ocupadas_estiba_25;

                break;
            case "estiba 26":
                aux=Utilidades.posisiones_ocupadas_estiba_26;

                break;
            case "estiba 27":
                aux=Utilidades.posisiones_ocupadas_estiba_27;

                break;
            case "estiba 28":
                aux=Utilidades.posisiones_ocupadas_estiba_28;

                break;
            case "estiba 29":
                aux=Utilidades.posisiones_ocupadas_estiba_29;

                break;
            case "estiba 30":
                aux=Utilidades.posisiones_ocupadas_estiba_30;

                break;
            case "estiba 31":
                aux=Utilidades.posisiones_ocupadas_estiba_31;

                break;
            case "estiba 32":
                aux=Utilidades.posisiones_ocupadas_estiba_32;

                break;
            case "estiba 33":
                aux=Utilidades.posisiones_ocupadas_estiba_33;

                break;
            case "estiba 34":
                aux=Utilidades.posisiones_ocupadas_estiba_34;

                break;
            case "estiba 35":
                aux=Utilidades.posisiones_ocupadas_estiba_35;

                break;
            case "estiba 36":
                aux=Utilidades.posisiones_ocupadas_estiba_36;

                break;
            case "estiba 37":
                aux=Utilidades.posisiones_ocupadas_estiba_37;

                break;
            case "estiba 38":
                aux=Utilidades.posisiones_ocupadas_estiba_38;

                break;
            case "estiba 39":
                aux=Utilidades.posisiones_ocupadas_estiba_39;

                break;
            case "estiba 40":
                aux=Utilidades.posisiones_ocupadas_estiba_40;

                break;
            case "estiba 41":
                aux=Utilidades.posisiones_ocupadas_estiba_41;

                break;
            case "estiba 42":
                aux=Utilidades.posisiones_ocupadas_estiba_42;

                break;
            case "estiba 43":
                aux=Utilidades.posisiones_ocupadas_estiba_43;

                break;
            case "estiba 44":
                aux=Utilidades.posisiones_ocupadas_estiba_44;

                break;
            case "estiba 45":
                aux=Utilidades.posisiones_ocupadas_estiba_45;

                break;
            case "estiba 46":
                aux=Utilidades.posisiones_ocupadas_estiba_46;

                break;
            case "estiba 47":
                aux=Utilidades.posisiones_ocupadas_estiba_47;

                break;
            case "estiba 48":
                aux=Utilidades.posisiones_ocupadas_estiba_48;

                break;
            case "estiba 49":
                aux=Utilidades.posisiones_ocupadas_estiba_49;

                break;

            case "estiba 50":
                aux=Utilidades.posisiones_ocupadas_estiba_50;

                break;
            case "estiba 51":
                aux=Utilidades.posisiones_ocupadas_estiba_51;

                break;
            case "estiba 52":
                aux=Utilidades.posisiones_ocupadas_estiba_52;

                break;
            case "estiba 53":
                aux=Utilidades.posisiones_ocupadas_estiba_53;

                break;
            case "estiba 54":
                aux=Utilidades.posisiones_ocupadas_estiba_54;

                break;
            case "estiba 55":
                aux=Utilidades.posisiones_ocupadas_estiba_55;

                break;
            case "estiba 56":
                aux=Utilidades.posisiones_ocupadas_estiba_56;

                break;
            case "estiba 57":
                aux=Utilidades.posisiones_ocupadas_estiba_57;

                break;
            case "estiba 58":
                aux=Utilidades.posisiones_ocupadas_estiba_58;

                break;
            case "estiba 59":
                aux=Utilidades.posisiones_ocupadas_estiba_59;

                break;
            case "estiba 60":
                aux=Utilidades.posisiones_ocupadas_estiba_60;

                break;
            case "estiba 61":
                aux=Utilidades.posisiones_ocupadas_estiba_61;

                break;
            case "estiba 62":
                aux=Utilidades.posisiones_ocupadas_estiba_62;

                break;
            case "estiba 63":
                aux=Utilidades.posisiones_ocupadas_estiba_63;

                break;
            case "estiba 64":
                aux=Utilidades.posisiones_ocupadas_estiba_64;

                break;
            case "estiba 65":
                aux=Utilidades.posisiones_ocupadas_estiba_65;

                break;
            case "estiba 66":
                aux=Utilidades.posisiones_ocupadas_estiba_66;

                break;
            case "estiba 67":
                aux=Utilidades.posisiones_ocupadas_estiba_67;

                break;
            case "estiba 68":
                aux=Utilidades.posisiones_ocupadas_estiba_68;

                break;
            case "estiba 69":
                aux=Utilidades.posisiones_ocupadas_estiba_69;

                break;
            case "estiba 70":
                aux=Utilidades.posisiones_ocupadas_estiba_70;

                break;
            case "estiba 71":
                aux=Utilidades.posisiones_ocupadas_estiba_71;

                break;
            case "estiba 72":
                aux=Utilidades.posisiones_ocupadas_estiba_72;

                break;
            case "estiba 73":
                aux=Utilidades.posisiones_ocupadas_estiba_73;

                break;
            case "estiba 74":
                aux=Utilidades.posisiones_ocupadas_estiba_74;

                break;
            case "estiba 75":
                aux=Utilidades.posisiones_ocupadas_estiba_75;

                break;
            case "estiba 76":
                aux=Utilidades.posisiones_ocupadas_estiba_76;

                break;
            case "estiba 77":
                aux=Utilidades.posisiones_ocupadas_estiba_77;

                break;
            case "estiba 78":
                aux=Utilidades.posisiones_ocupadas_estiba_78;

                break;
            case "estiba 79":
                aux=Utilidades.posisiones_ocupadas_estiba_79;

                break;
            case "estiba 80":
                aux=Utilidades.posisiones_ocupadas_estiba_80;

                break;
            case "estiba 81":
                aux=Utilidades.posisiones_ocupadas_estiba_81;

                break;
            case "estiba 82":
                aux=Utilidades.posisiones_ocupadas_estiba_82;

                break;
            case "estiba 83":
                aux=Utilidades.posisiones_ocupadas_estiba_83;

                break;
            case "estiba 84":
                aux=Utilidades.posisiones_ocupadas_estiba_84;

                break;
            case "estiba 85":
                aux=Utilidades.posisiones_ocupadas_estiba_85;

                break;
            case "estiba 86":
                aux=Utilidades.posisiones_ocupadas_estiba_86;

                break;
            case "estiba 87":
                aux=Utilidades.posisiones_ocupadas_estiba_87;

                break;
            case "estiba 88":
                aux=Utilidades.posisiones_ocupadas_estiba_88;

                break;
            case "estiba 89":
                aux=Utilidades.posisiones_ocupadas_estiba_89;

                break;
            case "estiba 90":
                aux=Utilidades.posisiones_ocupadas_estiba_90;

                break;
            case "estiba 91":
                aux=Utilidades.posisiones_ocupadas_estiba_91;

                break;
            case "estiba 92":
                aux=Utilidades.posisiones_ocupadas_estiba_92;

                break;
            case "estiba 93":
                aux=Utilidades.posisiones_ocupadas_estiba_93;

                break;
            case "estiba 94":
                aux=Utilidades.posisiones_ocupadas_estiba_94;

                break;
            case "estiba 95":
                aux=Utilidades.posisiones_ocupadas_estiba_95;

                break;
            case "estiba 96":
                aux=Utilidades.posisiones_ocupadas_estiba_96;

                break;
        }
        return aux;
    }
    public Button regresar_boton(String boton,View view){

        Button button=null;
        //button=view.findViewById(R.id.estiba1);

        return button;
    }
    }



