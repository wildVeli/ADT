package controlador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.GregorianCalendar;

//modificados el 31-01-17
// sobrecarga de int,float,double
//leerchar
//String introducirCadena(int)
//leerBoolean()
public class Utilidades {

		public static void main(String[] args) {
			//BORRAR
			
		}
		static double leerDouble (double min,double sup)
		{
			double num=0;
			boolean vale;
			do{
				try
				{
					num=(Double.parseDouble(introducirCadena()));
					vale=true;
				}catch(NumberFormatException e)
				{
					System.out.println("Introduce numeros");
					vale=false;
				}
				if (num<min|num>sup)
				{
					System.out.println("El número no está en el rango");
					vale=false;
				}
			}while(!vale);
			return num;
		}
		static double leerDouble ()
		{
			double num=0;
			boolean vale;
			do{
				try
				{
					num=(Double.parseDouble(introducirCadena()));
					vale=true;
				}catch(NumberFormatException e)
				{
					System.out.println("Introduce numeros");
					vale=false;
				}
			}while(!vale);
			return num;
		}
		static float leerFloat (float min,float sup)
		{
			float num=0;
			boolean vale;
			do{
				try
				{
					num=(Float.parseFloat(introducirCadena()));
					vale=true;
				}catch(NumberFormatException e)
				{
					System.out.println("Introduce numeros");
					vale=false;
				}
				if (num<min|num>sup)
				{
					System.out.println("El número no está en el rango");
					vale=false;
				}
			}while(!vale);
			return num;
		}
		static float leerFloat()
		{
			float num=0;
			boolean vale;
			do{
				try
				{
					num=(Float.parseFloat(introducirCadena()));
					vale=true;
				}catch(NumberFormatException e)
				{
					System.out.println("Introduce numeros");
					vale=false;
				}
			}while(!vale);
			return num;
		}
		static int leerInt (int min, int sup)
		{
			int num=0;
			boolean vale;
			do{
				try
				{
					num=(Integer.parseInt(introducirCadena()));
					vale=true;
				}catch(NumberFormatException e)
				{
					System.out.println("Introduce numeros");
					vale=false;
				}
				if (num<min|num>sup)
				{
					System.out.println("El número no está en el rango");
					vale=false;
				}
			}while(!vale);
			return num;
			
		}
		static int leerInt ()
		{
			int num=0;
			boolean vale;
			do{
				try
				{
					num=(Integer.parseInt(introducirCadena()));
					vale=true;
				}catch(NumberFormatException e)
				{
					System.out.println("Introduce numeros");
					vale=false;
				}
			}while(!vale);
			return num;
			
		}
		static String introducirCadena(){
			 String cadena="";
			 InputStreamReader entrada =new InputStreamReader(System.in);
			 BufferedReader teclado= new BufferedReader(entrada);
			 try{
			 cadena=teclado.readLine();
			 }
			 catch(IOException er){
			 System.out.println("error al introducir datos");
			 System.exit(0);
			 }
			 return cadena;
			}
		static String introducirCadena(int numCaracteres){
			String cadena="";
			boolean vale;
			do{
				vale=true;
					InputStreamReader entrada =new InputStreamReader(System.in);
				 	BufferedReader teclado= new BufferedReader(entrada);
				 try{
					 cadena=teclado.readLine();
				 }
				 catch(IOException er){
					 System.out.println("error al introducir datos");
					 System.exit(0);
				 }
				 if(cadena.length()!=numCaracteres)
				 {
					 System.out.println("Error, el dato introducido debe de ser de: "+numCaracteres+" caracteres");
					 vale=false;
				 }
			}while(!vale);
			 

			 return cadena;
			}

		static char leerChar (char caracteres[])
		{
			boolean error;
			String letra;
			short i = 0;
			do
			{
				error=false;
				letra=introducirCadena();
				if(letra.length()!=1)
				{
					System.out.println("Error, introduzca solo 1 letra");
				}
				else{
					for( i=0;i<caracteres.length;i++)
					{
						if(caracteres[i]==letra.charAt(0))
							break;
					}
				}
				if(i==caracteres.length)
					error=true;
			}while(error);
			return letra.charAt(0);
		}
		static char leerChar()
		{
			boolean error;
			String letra;
			do
			{
				error=false;
				letra=introducirCadena();
				if(letra.length()!=1)
				{
					System.out.println("Error, introduzca solo 1 letra");
					error=true;
				}		
			}while(error);
			return letra.charAt(0);
		}
		static boolean leerBoolean ()
		{
			boolean vale;
			boolean si = false;
			do{
				
				vale=true;
				String resp;
				System.out.println("Introduzca (Si/S/1/True) o (No/N/0/False)");
				resp=introducirCadena();
				resp.toUpperCase();
				if(resp.equals("SI")|resp.equals("S")|resp.equals("1")|resp.equals("TRUE"))
				{
					si=true;
				}else if(resp.equals("NO")|resp.equals("N")|resp.equals("0")|resp.equals("FALSE"))
				{
					si=false;
				}else{
						System.out.println("Error:Introduzca (Si/S/1/True) o (No/N/0/False)");
						vale=false;
				}
			}while(!vale);
			return si;
		}
		public static int calculoEdad(GregorianCalendar fech1,GregorianCalendar fech2){
			int anios=0;
			int plus=0;
			int d1=fech1.get(Calendar.DAY_OF_MONTH);
			int m1=fech1.get(Calendar.MONTH)+1;
			int a1=fech1.get(Calendar.YEAR);
			
			int a2=fech2.get(Calendar.YEAR);
			int m2=fech2.get(Calendar.MONTH)+1;
			int d2=fech2.get(Calendar.DAY_OF_MONTH);
						
			if((m2>m1)||((m2==m1)&&(d2>=d1))){
				plus=1;
			}
			
			anios=a2-a1-1+plus;
			
			return anios;
		}
		

	}

