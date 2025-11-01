/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.imss.dpes.renapo.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.dpes.common.model.BaseModel;

/**
 *
 * @author eduardo.loyo
 */
public class RenapoCurpOut extends BaseModel{
@Getter @Setter private Integer anioReg;
@Getter @Setter private String apellido1;
@Getter @Setter private String apellido2;
@Getter @Setter private Integer codigoError;
@Getter @Setter private String crip;
@Getter @Setter private String curp;
@Getter @Setter private String cveEntidadEmisora;
@Getter @Setter private Integer cveMunicipio;
@Getter @Setter private String desEntidadNac;
@Getter @Setter private String desEntidadRegistro;
@Getter @Setter private String desEstatusCURP;
@Getter @Setter private String desMunicipio;
@Getter @Setter private Integer docProbatorio;
@Getter @Setter private String estatusCURP;
@Getter @Setter private String fechNac;
@Getter @Setter private Integer foja;
@Getter @Setter private Integer folioCarta;
@Getter @Setter private Integer libro;
@Getter @Setter private String message;
@Getter @Setter private String nacionalidad;
@Getter @Setter private String nombres;
@Getter @Setter private Integer numActa;
@Getter @Setter private Integer numEntidadReg;
@Getter @Setter private Integer numRegExtranjeros;
@Getter @Setter private String sexo;
@Getter @Setter private String statusOper;
@Getter @Setter private Integer tipoError;
@Getter @Setter private Integer tomo;

}
