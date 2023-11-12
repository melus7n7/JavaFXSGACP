/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javafxsgacp.utilidades;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.PngImage;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import static javafx.scene.AccessibleAttribute.FONT;
import javafx.scene.control.Alert;
import javafxsgacp.JavaFXSGACP;
import javafxsgacp.modelo.pojo.Director;
import javafxsgacp.modelo.pojo.ExperienciaEducativa;
import javafxsgacp.modelo.pojo.FirmaFacultad;
import javafxsgacp.modelo.pojo.ImparticionExperienciaEducativa;
import javafxsgacp.modelo.pojo.Periodo;
import javafxsgacp.modelo.pojo.ProgramaEducativo;
import javafxsgacp.modelo.pojo.TrabajoDocente;
import javafxsgacp.modelo.pojo.Usuario;

/**
 *
 * @author sulem
 */
public class Constancia {
    public static byte[] generarConstanciaImpartirEE(ImparticionExperienciaEducativa trabajo, Usuario docente, FirmaFacultad firma){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Document documento = new Document();
        try {
            PdfWriter.getInstance(documento, byteArrayOutputStream);
            documento.setMargins(80, 80, 40, 40);
            documento.open();
            
            //Header
            String fuenteDireccion = JavaFXSGACP.class.getResource("recursos/fuentes/GILB____.TTF").toExternalForm();
            
            FontFactory.register(fuenteDireccion,"Gill Sans MT Bold");
            Font fuenteSubTitulo = FontFactory.getFont("Gill Sans MT Bold");
            fuenteSubTitulo.setSize(8);
            Paragraph parrafoFacultad = new Paragraph("Facultad de Estadística e Informática", fuenteSubTitulo);
            parrafoFacultad.setAlignment(Element.ALIGN_RIGHT);
            parrafoFacultad.setSpacingAfter(0);
            documento.add(parrafoFacultad);
            
            fuenteSubTitulo.setSize(7);
            Paragraph parrafoEspacio = new Paragraph(" ", fuenteSubTitulo);
            parrafoEspacio.setSpacingAfter(0);
            Paragraph parrafoRegion = new Paragraph("Región Xalapa", fuenteSubTitulo);
            parrafoRegion.setAlignment(Element.ALIGN_RIGHT);
            documento.add(parrafoEspacio);
            documento.add(parrafoEspacio);
            documento.add(parrafoRegion);
            
            //Inicio documento
            Font fuenteInicio = new Font(Font.FontFamily.TIMES_ROMAN);
            fuenteInicio.setSize(11);
            fuenteInicio.setStyle(Font.BOLD);
            
            Paragraph parrafoInicio = new Paragraph("A quien corresponda", fuenteInicio);
            documento.add(parrafoInicio);
            
            Paragraph parrafoEspacioInicio = new Paragraph(" ", fuenteInicio);
            documento.add(parrafoEspacioInicio);
            
            Font fuenteCuerpo = new Font(Font.FontFamily.TIMES_ROMAN);
            fuenteCuerpo.setSize(11);
            
            Paragraph parrafoIntroduccion = new Paragraph("El que suscribe, Director de la Facultad de Estadística e Informática, de la Universidad Veracruzana", fuenteCuerpo);
            parrafoIntroduccion.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(parrafoIntroduccion);
            
            Paragraph parrafoHaceConstar = new Paragraph("HACE CONSTAR", fuenteInicio);
            parrafoHaceConstar.setAlignment(Element.ALIGN_CENTER);
            documento.add(parrafoHaceConstar);
            documento.add(parrafoEspacioInicio);
            
            Chunk parteUno = new Chunk("que el Mtro. ", fuenteCuerpo);
            Chunk parteDocente = new Chunk(recuperarNombre(docente), fuenteInicio);
            Chunk parteDos = new Chunk(" impartió la siguiente experiencia educativa en el periodo ", fuenteCuerpo);
            Periodo periodo = trabajo.getExperienciaEducativa().getPeriodo();
            Chunk partePeriodo = new Chunk(periodo.getNombrePeriodo().toUpperCase(), fuenteInicio);

            Paragraph parrafoIntroduccionContinuacion = new Paragraph(); 
            parrafoIntroduccionContinuacion.add(parteUno); 
            parrafoIntroduccionContinuacion.add(parteDocente); 
            parrafoIntroduccionContinuacion.add(parteDos);
            parrafoIntroduccionContinuacion.add(partePeriodo);
            parrafoIntroduccionContinuacion.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(parrafoIntroduccionContinuacion);
            documento.add(parrafoEspacio);
            
            //Tabla Documento
            
            float[] anchoColumnas = {1, 1, 1.5f, 1};
            PdfPTable tabla = new PdfPTable(anchoColumnas);
            tabla.setWidthPercentage(100);
            
            PdfPCell celdaPrograma = new PdfPCell((new Phrase("Programa Educativo",fuenteInicio)));
            celdaPrograma.setFixedHeight(30f);
            celdaPrograma.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell celdaExperienciaEducativa = new PdfPCell((new Phrase("Experiencia Educativa",fuenteInicio)));
            celdaExperienciaEducativa.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell celdaBloque = new PdfPCell((new Phrase("Bloque/Sección/Créditos",fuenteInicio)));
            celdaBloque.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell celdaHsm = new PdfPCell((new Phrase("H/S/M",fuenteInicio)));
            celdaHsm.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            tabla.addCell(celdaPrograma);
            tabla.addCell(celdaExperienciaEducativa);
            tabla.addCell(celdaBloque);
            tabla.addCell(celdaHsm);
            
            ExperienciaEducativa experienciaEducativa = trabajo.getExperienciaEducativa();
            ProgramaEducativo programaEducativo = experienciaEducativa.getProgramaEducativo();
            
            PdfPCell celdaProgramaTrabajo = new PdfPCell
                ((new Phrase(programaEducativo.getNombreProgramaEducativo(),fuenteCuerpo)));
            celdaProgramaTrabajo.setFixedHeight(45f);
            celdaProgramaTrabajo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell celdaExperienciaEducativaTrabajo = new PdfPCell((new Phrase(experienciaEducativa.getNombre(),fuenteCuerpo)));
            celdaExperienciaEducativaTrabajo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell celdaBloqueTrabajo = new PdfPCell((new Phrase(recuperarBloqueSeccionCreditos(experienciaEducativa),fuenteCuerpo)));
            celdaBloqueTrabajo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            PdfPCell celdaHsmTrabajo = new PdfPCell((new Phrase(recuperarHSM(experienciaEducativa),fuenteCuerpo)));
            celdaHsmTrabajo.setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            tabla.addCell(celdaProgramaTrabajo);
            tabla.addCell(celdaExperienciaEducativaTrabajo);
            tabla.addCell(celdaBloqueTrabajo);
            tabla.addCell(celdaHsmTrabajo);
            
            documento.add(tabla);
            
            Chunk parteUnoFinal = new Chunk("A petición de la interesada y para los fines legales que a la misma convenga, "
                    + "se extiende la presente en la ciudad de Xalapa-Enríquez, Veracruz a los ", fuenteCuerpo);
            Chunk parteFecha = new Chunk(recuperarFechaActual(), fuenteInicio);
            Paragraph parrafoFinal = new Paragraph(); 
            parrafoFinal.add(parteUnoFinal); 
            parrafoFinal.add(parteFecha);
            parrafoFinal.setAlignment(Element.ALIGN_JUSTIFIED);
            documento.add(parrafoEspacioInicio);
            documento.add(parrafoFinal);
            documento.add(parrafoEspacioInicio);
            documento.add(parrafoEspacioInicio);
            
            Paragraph parrafoAtentamente = new Paragraph("A T E N T A M E N T E", fuenteCuerpo);
            parrafoAtentamente.setAlignment(Element.ALIGN_CENTER);
            documento.add(parrafoAtentamente);
            
            Paragraph parrafoLIS = new Paragraph("\"Lis de Veracruz: Arte, Ciencia, Luz\"", fuenteCuerpo);
            parrafoLIS.setAlignment(Element.ALIGN_CENTER);
            documento.add(parrafoLIS);
            documento.add(parrafoEspacioInicio);
            documento.add(parrafoEspacioInicio);
            
            Paragraph parrafoNombreDirector = new Paragraph(recuperarNombreDirectorCompleto(trabajo), fuenteInicio);
            parrafoNombreDirector.setAlignment(Element.ALIGN_CENTER);
            documento.add(parrafoNombreDirector);
            
            Paragraph parrafoDirector = new Paragraph("Director", fuenteCuerpo);
            parrafoDirector.setAlignment(Element.ALIGN_CENTER);
            documento.add(parrafoDirector);
            documento.add(parrafoEspacioInicio);
            documento.add(parrafoEspacioInicio);
            
            //FIRMA DE LA FACULTAD
            
            try{
                Image imagenFirma = PngImage.getImage(firma.getArchivoFirma());                
                imagenFirma.scaleAbsolute(80, 80);
                imagenFirma.setAlignment(Element.ALIGN_CENTER);
                documento.add(imagenFirma);
            }catch(DocumentException | IOException e){
                System.out.println(e);
            }
            documento.close();
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    private static String recuperarBloqueSeccionCreditos(ExperienciaEducativa experienciaEducativa){
        return experienciaEducativa.getBloque().getNombreBloque() + " - " + 
                experienciaEducativa.getSeccion().getNombreSeccion() + " - Créditos: " +
                experienciaEducativa.getCreditos();
    }
    
    private static String recuperarHSM(ExperienciaEducativa experienciaEducativa){
        return experienciaEducativa.getHsm() + " horas por semana al mes";
    }
    
    private static String recuperarNombreDirectorCompleto(TrabajoDocente trabajo){
        Director director = trabajo.getDirector();
        return director.getNombreDirector().toUpperCase() + " " + director.getApellidoPaterno().toUpperCase() + " "
                + director.getApellidoMaterno().toUpperCase();
    }
    
    private static String recuperarNombre(Usuario docente){
        return docente.getNombre().toUpperCase() + " " + docente.getApellidoPaterno().toUpperCase() + " " +
                docente.getApellidoMaterno().toUpperCase();
    }
    
    private static String recuperarFechaActual(){
        Date date = new Date();
        return Utilidades.formatoFechaEscrito(date);
    }
    
}
