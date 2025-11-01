/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.renapo.service;

import java.util.logging.Level;
import javax.interceptor.Interceptors;
import javax.ws.rs.ext.Provider;
import mx.gob.imss.dpes.common.exception.BusinessException;
import mx.gob.imss.dpes.common.model.Message;
import mx.gob.imss.dpes.common.model.ServiceStatusEnum;
import mx.gob.imss.dpes.common.service.ServiceDefinition;
import mx.gob.imss.dpes.interfaces.renapo.model.RenapoCurpOut;
import mx.gob.imss.dpes.interfaces.renapo.model.RenapoCurpRequest;
import mx.gob.imss.dpes.renapo.exception.RenapoCurpException;
import mx.gob.imss.dpes.renapo.ws.CurpKioscosBean;
import mx.gob.imss.dpes.renapo.ws.WsimsscurpSoap;
import mx.gob.imss.dpes.support.config.CustomSpringBeanAutowiringInterceptor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author eduardo.loyo
 */
@Provider
@Interceptors(CustomSpringBeanAutowiringInterceptor.class)
public class RenapoCurpService extends ServiceDefinition<RenapoCurpRequest, RenapoCurpRequest> {

    @Autowired
    private WsimsscurpSoap service;

    @Override
    public Message<RenapoCurpRequest> execute(Message<RenapoCurpRequest> request) throws BusinessException {
        //log.log(Level.INFO, "Request: {0}", request);
        CurpKioscosBean response = new CurpKioscosBean();
        try {
            response = service.consultaDatosCURP(request.getPayload().getRenapoCurpIn().getCurp());
        } catch (Exception e) {
            log.log(Level.SEVERE, "ERROR RenapoCurpService.execute = [" + request + "]", e);
            throw new RenapoCurpException();
        }
        //log.log(Level.INFO, "Response: {0}", response.getCodigoError());

        try {
            if (response.getCodigoError() == 0) {
                RenapoCurpOut renapoCurpOut = new RenapoCurpOut();
                renapoCurpOut.setAnioReg(response.getAnioReg());
                renapoCurpOut.setApellido1(response.getApellido1());
                renapoCurpOut.setApellido2(response.getApellido2());
                renapoCurpOut.setCrip(response.getCRIP());
                renapoCurpOut.setCurp(response.getCurp());
                renapoCurpOut.setCveEntidadEmisora(response.getCveEntidadEmisora());
                renapoCurpOut.setCveMunicipio(response.getCveMunicipioReg());
                renapoCurpOut.setDesEntidadNac(response.getDesEntidadNac());
                renapoCurpOut.setDesEntidadRegistro(response.getDesEntidadRegistro());
                renapoCurpOut.setDesEstatusCURP(response.getDesEstatusCURP());
                renapoCurpOut.setEstatusCURP(response.getEstatusCURP());
                renapoCurpOut.setFechNac(response.getFechNac());
                renapoCurpOut.setFoja(response.getFoja());
                renapoCurpOut.setFolioCarta(response.getFolioCarta());
                renapoCurpOut.setLibro(response.getLibro());
                renapoCurpOut.setMessage(response.getMessage());
                renapoCurpOut.setNacionalidad(response.getNacionalidad());
                renapoCurpOut.setNombres(response.getNombres());
                renapoCurpOut.setNumActa(response.getNumActa());
                renapoCurpOut.setNumEntidadReg(response.getNumEntidadReg());
                renapoCurpOut.setNumRegExtranjeros(response.getNumRegExtranjeros());
                renapoCurpOut.setSexo(response.getSexo());
                renapoCurpOut.setTomo(response.getTomo());

                request.getPayload().setRenapoCurpOut(renapoCurpOut);
                Message<RenapoCurpRequest> ret = new Message<>(request.getPayload());
                ret.getHeader().setStatus(ServiceStatusEnum.EXITOSO);
                return ret;
            }
        } catch(Exception e) {
            log.log(Level.SEVERE, "ERROR RenapoCurpService.execute = [" + request + "]", e);
        }

        //log.log(Level.INFO, ">>>exceptionrenapo: {0}", response.getCodigoError());
        throw new RenapoCurpException();
        //return response(null, ServiceStatusEnum.EXCEPCION, new RenapoCurpException(), null);
    }

}
