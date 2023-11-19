package Excel;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lists.Cola;
import lists.ListaSimple;
import model.Activity;
import model.Process;
import model.Task;
import model.User;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;


public class ExcelUtil {


    public static void exportToExcel(ListaSimple<Process> procesos, String[][] data, String filePath) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Proceso");

        int rowIndex = 0;

        // Crear las celdas de encabezado
        Row headerRow = sheet.createRow(rowIndex++);
        for (int j = 0; j < data[0].length; j++) {
            Cell headerCell = headerRow.createCell(j);

            Workbook wb = headerCell.getSheet().getWorkbook();
            CellStyle style = wb.createCellStyle();

            IndexedColors color = IndexedColors.LAVENDER;
            style.setFillForegroundColor(color.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            headerCell.setCellStyle(style);
            headerCell.setCellValue(data[0][j]);
        }

        // Llenar el resto de los datos desde la tabla
        for (int i = 0; i < procesos.getSize(); i++) {
            Process proceso = procesos.getNodeValue(i);
            Row row = sheet.createRow(rowIndex++);

            Cell cell = row.createCell(0);
            cell.setCellValue(proceso.getName());

            cell = row.createCell(1);
            cell.setCellValue(proceso.getActivitiesList().getSize());

            ListaSimple<Activity> actividades = proceso.getActivitiesList();

            if (!actividades.estaVacia()) {
                Row rowHeaderActivity = sheet.createRow(rowIndex++);
                Cell cellHeader = rowHeaderActivity.createCell(1);
                Cell cellHeader1 = rowHeaderActivity.createCell(2);
                Cell cellHeader2= rowHeaderActivity.createCell(3);
                Cell cellHeader3= rowHeaderActivity.createCell(4);

                Workbook wb = cell.getSheet().getWorkbook();
                CellStyle style = wb.createCellStyle();

                IndexedColors color = IndexedColors.RED;
                style.setFillForegroundColor(color.getIndex());
                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                cellHeader.setCellStyle(style);
                cellHeader.setCellValue("Activities");
                cellHeader1.setCellStyle(style);
                cellHeader1.setCellValue("Nombre Actividad");
                cellHeader2.setCellStyle(style);
                cellHeader2.setCellValue("Descripción");
                cellHeader3.setCellStyle(style);
                cellHeader3.setCellValue("Obligatoria");
            }

            int inicioAgrupacionActividades = rowIndex - 1;
            for (int j = 0; j < actividades.getSize(); j++) {
                Row row2 = sheet.createRow(rowIndex++);
                Cell cellActivity = row2.createCell(2);
                Cell cellActivity1 = row2.createCell(3);
                Cell cellActivity2 = row2.createCell(4);
                Activity activity = actividades.getNodeValue(j);
                cellActivity.setCellValue(activity.getName());
                cellActivity1.setCellValue(activity.getDescription());
                cellActivity2.setCellValue(activity.isMustDo());
                Cola<Task> tasks = activity.getTasksList();

                if (!tasks.estaVacia()) {
                    Row rowHeaderTasks = sheet.createRow(rowIndex++);
                    Cell cellHeader = rowHeaderTasks.createCell(2);
                    Cell cellHeader1 = rowHeaderTasks.createCell(3);
                    Cell cellHearder2 = rowHeaderTasks.createCell(4);
                    Cell cellHearder3 = rowHeaderTasks.createCell(5);

                    Workbook wb = cell.getSheet().getWorkbook();
                    CellStyle style = wb.createCellStyle();

                    IndexedColors color = IndexedColors.GREEN;
                    style.setFillForegroundColor(color.getIndex());
                    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

                    cellHeader.setCellStyle(style);
                    cellHeader.setCellValue("Tasks");
                    cellHeader1.setCellStyle(style);
                    cellHeader1.setCellValue("Description");
                    cellHearder2.setCellStyle(style);
                    cellHearder2.setCellValue("Obligatoria");
                    cellHearder3.setCellStyle(style);
                    cellHearder3.setCellValue("Duración");

                }

                int inicioAgrupacionTareas = rowIndex - 1;
                for (int k = 0; k < tasks.tamanio; k++) {
                    Row row3 = sheet.createRow(rowIndex++);
                    Task task = tasks.getNodeValue(k);
                    Cell cellTasks = row3.createCell(3);
                    Cell cellTasks1 = row3.createCell(4);
                    Cell cellTasks2 = row3.createCell(5);
                    cellTasks.setCellValue(task.getDescription());
                    cellTasks2.setCellValue(task.getDuration());
                    cellTasks1.setCellValue(task.isMustDo());
                }
            }
            if (!actividades.estaVacia()) {
                sheet.createRow(rowIndex++);
                sheet.groupRow(inicioAgrupacionActividades, rowIndex - 1);
            }
        }

        if (!procesos.estaVacia()) {
            sheet.createRow(rowIndex++);
            // Agrupar las filas
            sheet.groupRow(1, rowIndex - 1); // Agrupa desde la segunda fila (fila de datos) hasta la última fila
        }
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static ListaSimple<Process> importFromExcel(String filePath) {

        ListaSimple<Process> procesos = new ListaSimple<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                String nombreProceso = getCellValueAsString(row.getCell(0));
                String numeroActividades = getCellValueAsString(row.getCell(1));
                int numActividades = (int) Double.parseDouble(numeroActividades);


                Process proceso = new Process();
                proceso.setName(nombreProceso);
                proceso.setSize(numActividades);

                System.out.println("nombre proeceso: " + nombreProceso);
                System.out.println("Numero actividad: " + numeroActividades);

                if (!numeroActividades.isEmpty()) {
                    rowIndex++;
                    rowIndex++;

                    for (int i = 0; i < numActividades; i++) {
                        System.out.println("posicion de la actividad" + rowIndex);
                        System.out.println("actividad tal: " + i);
                        Row activityRow = sheet.getRow(rowIndex++);

                        String nombreActividad = getCellValueAsString(activityRow.getCell(2));
                        String descripcionActividad = getCellValueAsString(activityRow.getCell(3));
                        boolean mustDoActividad = Boolean.parseBoolean(getCellValueAsString(activityRow.getCell(4)));

                        System.out.println("Nombre Actividad: " + nombreActividad);


                        Activity actividad = new Activity();

                        actividad.setName(nombreActividad);
                        actividad.setDescription(descripcionActividad);
                        actividad.setMustDo(mustDoActividad);

                        proceso.getActivitiesList().addToEnd(actividad);

                        Row nextRow = sheet.getRow(rowIndex);
                        if (nextRow.getCell(2) != null && nextRow.getCell(2).getStringCellValue().equals("Tasks")) {
                            rowIndex++;

                            for (; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                                nextRow = sheet.getRow(rowIndex);

                                Row taskRow = sheet.getRow(rowIndex);
                                String descripcionTarea = getCellValueAsString(taskRow.getCell(3));
                                boolean mustDoTarea = Boolean.parseBoolean(getCellValueAsString(taskRow.getCell(4)));
                                String duracionTarea = getCellValueAsString(taskRow.getCell(5));

                                System.out.println("Descipcion tarea: " + descripcionTarea);

                                Task tarea = new Task();

                                tarea.setDescription(descripcionTarea);
                                tarea.setDuration(duracionTarea);
                                tarea.setMustDo(mustDoTarea);
                                actividad.getTasksList().encolar(tarea);

                                // Check if there are more tasks in this activity
                                Row nextTaskRow = sheet.getRow(rowIndex + 1);
                                if (nextTaskRow.getCell(3) != null) {
                                    System.out.println("continuo");
                                    continue;
                                } else {
                                    System.out.println("breakio y posicion actual es: "+ rowIndex);
                                    rowIndex++;
                                    rowIndex++;
                                    break;
                                }
                            }
                        }
                    }
                }

                procesos.addToEnd(proceso);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return procesos;
    }


    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return ""; // Devuelve una cadena vacía si la celda es nula
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return Double.toString(cell.getNumericCellValue());
                }
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }
}
