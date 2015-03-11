package de.markant.ntv.common.rest.interfaces.sys;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import de.markant.ntv.common.dto.sys.BenutzerDto;
import de.markant.ntv.common.dto.sys.RolleDto;

/**
 *
 * IBenutzerEndpoint.java
 *
 * Definiert die Endpoints für den Zugriff auf die Benutzer in der Datenbank.
 * CRUD.
 *
 * @author weberrussc
 * @version 09.01.2015
 *
 */
@Path("benutzer")
public interface IBenutzerEndpoint {

    /**
     * Liefert den Benutzer anhand seiner mnetId.
     *
     * @param mnetId
     *        markant.net id (Login) eines Benutzers
     *
     * @return
     *         Benutzer sofern vorhanden, sonst <code>NULL</code>
     */
    @GET
    @Path("mnetId/{mnetId}/")
    @Produces(MediaType.APPLICATION_JSON)
    BenutzerDto findByMnetId(@PathParam("mnetId") String mnetId);

    /**
     * Ermittelt das RechteSet eines Benutzers für einen bestimmten Mandanten.
     *
     * @param mnetId
     *        markant.net id (Login) eines Benutzers
     * @param mandantId
     *        ID des Mandanten
     *
     * @return
     *         Rechteset
     */
    @GET
    @Path("rechte/mnetId/{mnetId}/mandantId/{mandantId}/")
    @Produces(MediaType.APPLICATION_JSON)
    Set<String> getRechteByMnetIdAndMandantId(@PathParam("mnetId") String mnetId, @PathParam("mandantId") Long mandantId);

    /**
     * Ermittelt eine Liste von Rollen eines Benutzers für einen bestimmten Mandanten.
     *
     * @param mnetId
     *        markant.net id (Login) eines Benutzers
     * @param mandantId
     *        ID des Mandanten
     *
     * @return
     *         Rollenliste
     */
    @GET
    @Path("rollen/mnetId/{mnetId}/mandantId/{mandantId}/")
    @Produces(MediaType.APPLICATION_JSON)
    List<RolleDto> getRollenByMnetIdAndMandantId(@PathParam("mnetId") String mnetId, @PathParam("mandantId") Long mandantId);

    /**
     * Erstellt oder aktualisiert einen Benutzer.
     *
     * @param benutzerDto
     *        Benutzer, der zu erstellen oder aktualisieren ist
     * @param bearbeiter
     *        Bearbeiter der Operation
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void createUpdateBenutzer(BenutzerDto benutzerDto, @QueryParam("bearbeiter") String bearbeiter);

    /**
     * Aktualisiert die Rollen eines Benutzers für einen bestimmten Mandanten.
     *
     * @param rollen
     *        Rollenliste mit den aktuellen Rollen für einen Mandanten
     * @param mnetId
     *        markant.net id (Login) eines Benutzers
     * @param mandantId
     *        ID des Mandanten
     * @param bearbeiter
     *        Bearbeiter der Operation
     */
    @PUT
    @Path("rollen/mnetId/{mnetId}/mandantId/{mandantId}/")
    @Consumes(MediaType.APPLICATION_JSON)
    void updateBenutzerRollen(List<RolleDto> rollen, @PathParam("mnetId") String mnetId, @PathParam("mandantId") Long mandantId,
            @QueryParam("bearbeiter") String bearbeiter);

    /**
     * Löscht den Benutzer über seine technische ID.
     * Zuvor wird noch der löschende Bearbeiter hinterlegt.
     *
     * @param id
     *        ID des Benutzers
     * @param bearbeiter
     *        Bearbeiter der Operation
     */
    @DELETE
    @Path("id/{id}/")
    @Consumes(MediaType.APPLICATION_JSON)
    void deleteById(@PathParam("id") Long id, @QueryParam("bearbeiter") String bearbeiter);

    /**
     * Liefert eine Liste aller Benutzer.
     *
     * @return
     *         Liste aller Benutzer, falls keine Benutzer vorhanden leere Liste
     */
    @GET
    @Path("benutzer/")
    @Produces(MediaType.APPLICATION_JSON)
    List<BenutzerDto> getAll();

}
