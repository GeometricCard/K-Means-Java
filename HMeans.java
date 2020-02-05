/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Kmeans;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ElTera
 */

public class HMeans {
  public int n;
  public int c;  
  //public int m; //depende grado de Fuzzi
  
  protected double matrizPuntos[][];
  protected double centroides[][];
  protected double matrizDistancia[][];
  double matrizDePertenenciaH[][];
  double cardinalidad[];
  private double sumaPertenenciaCentroides[][];
  private double matrizCosto[];
  protected double costoFinal;
  
   public ArrayList<ArrayList<Integer>> ListaDePuntos = new ArrayList<ArrayList<Integer>>();
  
    public HMeans(int n, int c){
        this.n = n;
        this.c = c;
        matrizPuntos = new double[n][2];
        centroides = new double [c][2];
        matrizDistancia = new double [c][n];
        matrizDePertenenciaH = new double[c][n];
        cardinalidad = new double[c];
        sumaPertenenciaCentroides = new double[c][2];
        matrizCosto = new double [c];
        
    }
    
    public HMeans(){
        
    }
    
    public void SetCentroides()
    {
         System.out.println("Ingresar Centroides");
        Scanner in = new Scanner(System.in);
  
        for(int i = 0; i < c; i++)
        {
            for(int j= 0; j < 2; j++)
            {
                centroides[i][j] = in.nextDouble();
            }
        }
 
    }
    
    public void SetCentroidesTabla(int i, int j,double val)
    {   
                centroides[i][j] = val;       
    }
    
    
    
     public void SetPuntos()
    {
         
        Scanner in = new Scanner(System.in);
  
        System.out.println("Ingresar Puntos");
       
        for(int i = 0; i < n; i++)
        {
            for(int j= 0; j < 2; j++)
            {              
                matrizPuntos[i][j] = in.nextDouble();
            }
        }

    }
     
     public void MostrarPuntos()
    {
         
        System.out.println("Puntos");
       
        for(int i = 0; i < n; i++)
        {
            for(int j= 0; j < 2; j++)
            {              
              System.out.print(matrizPuntos[i][j] + " ");
            }
            System.out.println();
        }

    }
     
     public double GetPuntos(int x, int y){
         return matrizPuntos[x][y];
     }
     
      public void SetPuntosTabla(int i, int j,double val)
    {
                matrizPuntos[i][j] = val;
    }
      
      
     
     protected void DistanciaEuclidiana(){
         int matrizPuntosFilas = 0;
         int matrizPuntosColumnas = 0;
         int centroidesFilas = 0;
         int centroidesColumnas = 0;
         
         for (int i = 0; i < c ; i++){
             for (int j =0; j < n; j++){
                 matrizDistancia[i][j] = Math.sqrt(Math.pow(matrizPuntos[matrizPuntosFilas][matrizPuntosColumnas] - 
                 centroides[centroidesFilas][centroidesColumnas], 2) + Math.pow(matrizPuntos[matrizPuntosFilas][matrizPuntosColumnas + 1] - 
                 centroides[centroidesFilas][centroidesColumnas + 1], 2));
                 
                 matrizPuntosFilas++;
             }
             centroidesFilas++;
             matrizPuntosFilas = 0;
         }
     }
     
     public void MostrarMatrizDistancia(){
           System.out.println("Matriz Distancia");    
         for (int i = 0; i < c ; i++){
             for (int j =0; j < n; j++){
                 
                 System.out.print(matrizDistancia[i][j] + " ");    
                 
             }
             System.out.println();
         }
     }
     
     public void MatrizDePertenenciaHardMeans(){
         
         for(int i = 0; i < c; i++){
            for (int j = 0; j < n; j++){
                matrizDePertenenciaH[i][j] = 0;
            }
        }
         
         double aux,auxa ;
         int a,b;
         for(int i=0; i < n; i++){ 
             aux = matrizDistancia[0][i];
             a = 0;
             b = i;
             for (int j=1; j < c; j++){
               auxa = matrizDistancia[j][i];
               if(auxa <= aux){
                   a = j;
                   b = i;
                   aux = matrizDistancia[j][i];
               }
               
             }
             matrizDePertenenciaH[a][b] = 1;
             
         }
           
         
//         int matrizDistanciaFilas = 0;
//         int matrizDistanciaColumnas = 0;
//        for(int i = 0; i < c; i++){
//         for (int j = 0; j < n ; j++){
//             
//          if( matrizDistancia[i][j] <= matrizDistancia[1 + matrizDistanciaFilas][ matrizDistanciaColumnas] )
//          {
//              matrizDePertenenciaH[matrizDistanciaFilas][i] = 1;
//              matrizDePertenenciaH[1 + matrizDistanciaFilas][i] = 0;
//              //incrementar columnas
//              matrizDistanciaColumnas++;
//          }
//          else
//          {
//              matrizDePertenenciaH[matrizDistanciaFilas][i] = 0;
//              matrizDePertenenciaH[1 + matrizDistanciaFilas][i] = 1;
//              matrizDistanciaColumnas++;
//          }
//           
//       
//         }
//        }
         
     }
     
      public int GetFilaPertenecia(int columna){
          int fila = 0;
          for(int i = 0; i < c; i++){
              
            if(matrizDePertenenciaH[i][columna] == 1)
            {
                fila = i;
            }   
            
        }
          
          return fila;
      }
     
     public void MostrarGrupoPuntos(){
         
         for(int i=0;i<ListaDePuntos.size();i++){  //para cada alumno (para cada fila)
            System.out.print("Alumno " + i + ": ");
            for(int j=0;j<ListaDePuntos.get(i).size();j++){  //se recorre todas la columnas de la fila
                System.out.print(ListaDePuntos.get(i).get(j) + " "); //se obtiene el elemento i,j
            }
            System.out.println();          
        }
     }
     
     public void MostrarMatrizPertenencia(){
         System.out.println("Matriz Pertenencia");    
         for (int i = 0; i < c ; i++){
             for (int j =0; j < n; j++){
                 
                 System.out.print(matrizDePertenenciaH[i][j] + " ");    
                 
             }
             System.out.println();
         }
     }
     
     public double ObtenerMostrarMatrizPertenencia(int i,int j){
                      
                return matrizDePertenenciaH[i][j];
    
     }
     
     public ArrayList ListaDeElmentos(int grupo){
         return ListaDePuntos.get(grupo-1);
     }
     
     protected void CalcularCardinalidad(){
        
         if(!ListaDePuntos.isEmpty()){
             for(int i=0;i<ListaDePuntos.size();i++){  //para cada alumno (para cada fila)
            for(int j=0;j<ListaDePuntos.get(i).size();j++){  //se recorre todas la columnas de la fila
                ListaDePuntos.get(i).clear();
            }         
        }
         }
         
         if(ListaDePuntos.isEmpty()){
             for(int i = 0; i< c; i++){
                 ListaDePuntos.add(new ArrayList<Integer>());
             }
         }
        
         for (int i = 0; i < c ; i++){
             cardinalidad[i] = 0;
         }
           
         for (int i = 0; i < c ; i++){
             
             for (int j =0; j < n; j++){
                 
                 if(matrizDePertenenciaH[i][j] == 1){
                     ListaDePuntos.get(i).add(j);
                     cardinalidad[i] += 1;
                 }    
                 
             }
             
         }
     }
     
     public void MostrarCardinalidad(){
         System.out.println("Cardinalidad");
          for (int i = 0; i < c ; i++){
   
              System.out.print(cardinalidad[i] + " ");       
                
         }
          System.out.println();
     }
     
     public double GetCardinalidad(int noCentroide){         
       return cardinalidad[(noCentroide-1)];     
     }
     
     protected void CalcularNuevosCentroides(){
         
         for (int i = 0; i < c ; i++){
             matrizCosto[i] = 0;
             for (int j =0; j < 2; j++){
 
                   sumaPertenenciaCentroides[i][j] = 0;

             }
  
         }
         
         int sumaPertenenciaFilas = 0;
         int sumaPertenenciaColumnas = 0;
          for (int i = 0; i < c ; i++){
             for (int j =0; j < n; j++){
                 
               if(matrizDePertenenciaH[i][j] == 1)
               {
                   sumaPertenenciaCentroides[sumaPertenenciaFilas][sumaPertenenciaColumnas] += matrizPuntos[j][sumaPertenenciaColumnas];
                   sumaPertenenciaCentroides[sumaPertenenciaFilas][sumaPertenenciaColumnas + 1] += matrizPuntos[j][sumaPertenenciaColumnas + 1];//aumentar col
                   matrizCosto[i] += matrizDistancia[i][j];             
               }
                 
             }
            sumaPertenenciaFilas++;
         }
         
         int centroidesColumnas = 0;
         
          for (int i = 0; i < c ; i++){
//              System.out.print(centroides[i][centroidesColumnas] + " ");
//              System.out.print(centroides[i][centroidesColumnas + 1] + " ");
//              System.out.print(1/cardinalidad[i]);
                  centroides[i][centroidesColumnas] = (1/cardinalidad[i])*(sumaPertenenciaCentroides[i][centroidesColumnas]);
                  
                  centroides[i][centroidesColumnas + 1] = (1/cardinalidad[i])*(sumaPertenenciaCentroides[i][centroidesColumnas + 1]);
                  
         }
          
     }
     
     
     public void MostrarSumasPertenencia(){
          System.out.println("Sumas Pertenencia");
          for (int i = 0; i < c ; i++){
             for (int j =0; j < 2; j++){
                 
               System.out.print(sumaPertenenciaCentroides[i][j] + " ");
                 
             }
           System.out.println();
         }
     }
     
     public void MostrarCentroides(){

         System.out.println("Matriz Centroides");
         
          for (int i = 0; i < c ; i++){
             for (int j =0; j < 2; j++){
                 
               System.out.print(centroides[i][j] + " ");
                 
             }
             System.out.println();
         }
     }
     
     public double GetCentroides(int i, int j){
         return centroides[i][j];
     }
     
     public double GetMatrizCosto(int i){
         return matrizCosto[i];
     }
     
     private void CalcularCosto(){
         costoFinal = 0;
          for (int i = 0; i < c ; i++){
             costoFinal += matrizCosto[i];
         }
     }
     
     public double GetCosto(){
         return costoFinal;
     }
     
     public void MostrarCostos(){
         CalcularCosto();
         System.out.println("Matriz Costo");
         
          for (int i = 0; i < c ; i++){
             System.out.print(matrizCosto[i] + " ");
             System.out.print(costoFinal + " ");
         }
           System.out.println();
     }
     
     public void RealizarIteracion(){
         DistanciaEuclidiana();
         MostrarMatrizDistancia();
         MatrizDePertenenciaHardMeans();
         MostrarMatrizPertenencia();
         CalcularCardinalidad();
         MostrarCardinalidad();
         CalcularNuevosCentroides();
         MostrarSumasPertenencia();
         MostrarCentroides();
         MostrarCostos();
     }
}
