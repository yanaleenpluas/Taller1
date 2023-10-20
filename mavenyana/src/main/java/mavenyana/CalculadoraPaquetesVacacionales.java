package mavenyana;

import java.util.Scanner;
//CHECKSTYLE:OFF

public final class CalculadoraPaquetesVacacionales {
/**.
*Representation Costo base
*/
    private static final int COSTO_BASE = 1000;
    /**.
    *Costo asicional Paris
    */
    private static final int COST_PARIS = 500;
    /**.
    *Costo adicional newyork
    */
    private static final int COST_NEWYORK = 600;
    /**.
     *Costo adicional base
     */
    private static  int costoAdicional = 0;
    /**.
     *Costo total inicial
     */
    private static  int costoTotal = 0;
    /**.
     Numero de viajeros minimo
     */
    private static final int NUMTRAVELERMIN = 4;
    /**.
     *Numero maximo de viajeros
     */
    private static final int NUMTRAVELERMAX = 10;
    /**.
     *Descuento dias minimo
     */
    private static final int DAYSMIN = 7;
    /**.
     *Descuento dias maximo
     */
    private static final int DAYSMAX = 30;
    /**.
     *Multa
     */
    private static final int MULTA = 200;
    /**.
     *Promocion
     */
    private static final int PROMOTION = 200;
    /**.
     *Exceso de viajeros
     */
    private static final int  EXCESOTRAVELER = 80;
    /**.
     *Descuento minimo
     */
    private static final double  DISCONTNIM = 0.10;
    /**.
     *Descuento maximo
     */
    private static final double  DISCONTMAX = 0.20;

    /**.
     *Metodo main
     *@param args not used
     */
    public static void main(final String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Ingrese el destino de las vacaciones: ");
        String destino = sc.nextLine().toLowerCase();
        System.out.print("Ingrese el número de viajeros: ");
        int numeroViajeros = sc.nextInt();
        System.out.print("Ingrese la duración de las vacaciones en días: ");
        int duracionVacaciones = sc.nextInt();
        sc.close();

        //Cost Adicional Destino
        if (destino.equals("parís")) {
        	costoAdicional = COST_PARIS;
        } else if (destino.equals("nueva york")) {
        	costoAdicional = COST_NEWYORK;
        }
        costoTotal = COSTO_BASE + costoAdicional;
        boolean condition1 = numeroViajeros > NUMTRAVELERMIN;
        boolean condition2 = numeroViajeros <= NUMTRAVELERMAX;
        //Descruento Viajeros
        if (condition1 && condition2) {
        	costoTotal -= (int) (costoTotal * DISCONTNIM);
        } else if (numeroViajeros > NUMTRAVELERMAX) {
        	costoTotal -= (int) (costoTotal * DISCONTMAX);
        }
        //Multa duraccion & descuento promocion
        if (duracionVacaciones < DAYSMIN) {
        	costoTotal  += MULTA;
        } else if (duracionVacaciones > DAYSMAX || numeroViajeros == 2) {
        	costoTotal -= PROMOTION;
        }

        if (numeroViajeros > EXCESOTRAVELER) {
            System.out.println("El paquete no está disponible.");
            System.out.println("para grupo mayores a 80 personas.");

            costoTotal = -1;
        } else {
            System.out.println("El costo total es: $" + costoTotal);
        }
    }
  //CHECKSTYLE:ON

}

