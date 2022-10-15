/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package py.una.pol.personas.rest;


//Se creo una clase Rest para hacer las peticiones y se comento algunos codigos que no se utilizaría
//Lucas Candia


import py.una.pol.personas.model.Factura;
import py.una.pol.personas.model.Persona;
import py.una.pol.personas.service.FacturaService;
import py.una.pol.personas.service.PersonaService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * JAX-RS Example
 * <p/>
 * Esta clase produce un servicio RESTful para leer y escribir contenido de personas
 */
@Path("/factura")
@RequestScoped
public class FacturaRESTService {

    @Inject
    private Logger log;

    @Inject
    FacturaService facturaService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Factura> listar() {
        return facturaService.seleccionar();
    }

   /* @GET
    @Path("/{ruc:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Factura obtenerPorId(@PathParam("ruc") String ruc) {
        Factura p = facturaService.seleccionarPorRuc(ruc);
        if (p == null) {
        	log.info("obtenerPorId " + ruc + " no encontrado.");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        log.info("obtenerPorId " + ruc + " encontrada: " + p.getRuc() + " " + p.getRazonSocial()+ " "+p.getFecha()+" "+ p.getMonto());
        return p;
    }*/

    @GET
    @Path("/ruc")
    @Produces(MediaType.APPLICATION_JSON)
    public Factura obtenerPorIdQuery(@QueryParam("ruc") String ruc) {
        Factura p = facturaService.seleccionarPorRuc(ruc);
        if (p == null) {
        	log.info("obtenerPorId " + ruc + " no encontrado.");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        log.info("obtenerPorId " + ruc + " encontrada: " + p.getRuc() + " " + p.getRazonSocial()+ " "+p.getFecha()+" "+ p.getMonto());
        return p;
    }

    
    
    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(Factura f) {

        Response.ResponseBuilder builder = null;

        try {
            facturaService.crear(f);
            // Create an "ok" response
            
            //builder = Response.ok();
            builder = Response.status(201).entity("Factura creada exitosamente");

            
        } catch (SQLException e) {
            // Handle the unique constrain violation
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("bd-error", e.getLocalizedMessage());
            builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
        } catch (Exception e) {
            // Handle generic exceptions
            Map<String, String> responseObj = new HashMap<>();
            responseObj.put("error", e.getMessage());
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseObj);
        }

        return builder.build();
    }

//   @DELETE
//   //@Path("/{cedula}")
//   public Response borrar(@QueryParam("cedula") long cedula)
//   {
//	   Response.ResponseBuilder builder = null;
//	   try {
//
//		   if(personaService.seleccionarPorCedula(cedula) == null) {
//
//			   builder = Response.status(Response.Status.NOT_ACCEPTABLE).entity("Persona no existe.");
//		   }else {
//			   personaService.borrar(cedula);
//			   builder = Response.status(202).entity("Persona borrada exitosamente.");
//		   }
//
//
//
//	   } catch (SQLException e) {
//           // Handle the unique constrain violation
//           Map<String, String> responseObj = new HashMap<>();
//           responseObj.put("bd-error", e.getLocalizedMessage());
//           builder = Response.status(Response.Status.CONFLICT).entity(responseObj);
//       } catch (Exception e) {
//           // Handle generic exceptions
//           Map<String, String> responseObj = new HashMap<>();
//           responseObj.put("error", e.getMessage());
//           builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
//       }
//       return builder.build();
//   }
   
}
