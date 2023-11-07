package mavenyana;

import java.util.Scanner;
import java.util.Locale;
import java.util.logging.Logger;


//CHECKSTYLE:OFF
//Classs 
public final class CalculadoraPaquetesVacacionales {
    private static final Logger LOGGER = Logger.getLogger(CalculadoraPaquetesVacacionales.class.getName());
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
    private static  int costoAdicional;
    /**.
     *Costo total inicial
     */
    private static  int costoTotal;
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
     *Descuento maximo
     */
    private static final double ALL_INCLUSIVE_PAC = 200.0;
    /**.
     *Descuento maximo
     */
    private static final double ADVENTURE_PACKAGE = 150.0;
    /**.
     *Descuento maximo
     */
    private static final double SPA_PACKAGE = 100.0;
    /**.
     *Metodo main
     *@param args not used
     */
    public static void main(final String[] args) {
        final Scanner scanner1  = new Scanner(System.in);
        LOGGER.info("Ingrese el destino de las vacaciones");
        final String destino = scanner1.nextLine().toLowerCase(Locale.ENGLISH);
        LOGGER.info("Ingrese el número de viajeros: ");
        final int numeroViajeros = scanner1.nextInt();
        LOGGER.info("Ingrese la duración de las vacaciones en días: ");
        final int duraticonVacat = scanner1.nextInt();

        final double addOnsCost = calculateAdditioPackagesCost(scanner1);
        scanner1.close();
        final String newyork = "nueva york";
        final String paris = "parís";

        if (paris.equals(destino)) {
            costoAdicional = COST_PARIS;
        } else if (newyork.equals(destino)) {
            costoAdicional = COST_NEWYORK;
        }
        costoTotal = COSTO_BASE + costoAdicional;
        final boolean bool1 = numeroViajeros > NUMTRAVELERMIN;
        final boolean bool2 = numeroViajeros <= NUMTRAVELERMAX;

        if (bool1 && bool2) {
            costoTotal -= (int) (costoTotal * DISCONTNIM);
        } else if (numeroViajeros > NUMTRAVELERMAX) {
            costoTotal -= (int) (costoTotal * DISCONTMAX);
        }

        if (duraticonVacat < DAYSMIN) {
            costoTotal += MULTA;
        } else if (duraticonVacat > DAYSMAX || numeroViajeros == 2) {
            costoTotal -= PROMOTION;
        }

        costoTotal += addOnsCost;

        if (numeroViajeros > EXCESOTRAVELER) {
            LOGGER.info("El paquete no disponible grupos mayores a 80 pers.");
            costoTotal = -1;
        } else {
            LOGGER.info("El costo total es: $" + costoTotal);
        }
    }

    private static double calculateAdditioPackagesCost(final Scanner scanner) {
        double additionalCost = 0;
        LOGGER.info("Seleccione los add-ons para su paquete de vacaciones:");
        LOGGER.info("1. All-Inclusive Package - $200 por viajero");
        LOGGER.info("2. Adventure Activities Package - $150 por viajero");
        LOGGER.info("3. Spa and Wellness Package - $100 por viajero");
        LOGGER.info("Ingrese el # del add-on incluira(o 0 para finalizar):");
        while (true) {
            final int selectedPackage = getValidQuantity(scanner.nextLine());
            switch (selectedPackage) {
                case 1:
                    additionalCost += ALL_INCLUSIVE_PAC;
                    break;
                case 2:
                    additionalCost += ADVENTURE_PACKAGE;
                    break;
                case 3:
                    additionalCost += SPA_PACKAGE;
                    break;
                case 0:
                    return additionalCost;
                default:
                    LOGGER.info("#de add-on invalido");
                    LOGGER.info("Seleccione un add-on válido o 0 para fin.");
                    break;
            }
        }
    }

    private static int getValidQuantity(final String input) {
        try {
            final int quantity = Integer.parseInt(input);
            return quantity >= 0 ? quantity : -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}


//CHECKSTYLE:ON
