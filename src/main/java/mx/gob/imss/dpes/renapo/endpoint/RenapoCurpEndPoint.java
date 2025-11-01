/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.renapo.endpoint;

import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import mx.gob.imss.dpes.common.endpoint.BaseGUIEndPoint;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.renapo.model.RenapoCurpIn;
import mx.gob.imss.dpes.interfaces.renapo.model.RenapoCurpOut;
import mx.gob.imss.dpes.interfaces.renapo.model.RenapoCurpRequest;
import mx.gob.imss.dpes.interfaces.sistrap.model.IncidenciaTitularGrupo;
import mx.gob.imss.dpes.renapo.service.RenapoCurpService;
import org.eclipse.microprofile.openapi.annotations.Operation;



/**
 *
 * @author eduardo.loyo
 */
@Path("/renapo")
@RequestScoped
public class RenapoCurpEndPoint extends BaseGUIEndPoint<RenapoCurpRequest, RenapoCurpOut, RenapoCurpRequest> {

  @Inject
    private RenapoCurpService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Consumo de RENAPO",
            description = "Info del pensionado")
    public Response load(RenapoCurpIn request) throws BusinessException {
    	RenapoCurpRequest renapoCurpRequest = new RenapoCurpRequest();
    	renapoCurpRequest.setRenapoCurpIn(request);
        ServiceDefinition[] steps = {service};
        Message<RenapoCurpRequest> renapoResponse
                = service.executeSteps(steps, new Message<>(renapoCurpRequest));
        return toResponse(new Message<>( renapoResponse.getPayload().getRenapoCurpOut()));
    }
}
