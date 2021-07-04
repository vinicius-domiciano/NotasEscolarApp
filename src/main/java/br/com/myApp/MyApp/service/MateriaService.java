package br.com.myApp.MyApp.service;

import br.com.myApp.MyApp.exceptions.BadRequestException;
import br.com.myApp.MyApp.exceptions.NotFoundException;
import br.com.myApp.MyApp.model.Materia;
import br.com.myApp.MyApp.model.converters.MateriaConverter;
import br.com.myApp.MyApp.model.dto.materia.MateriaDefaultDTO;
import br.com.myApp.MyApp.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;
import java.util.UUID;

public class MateriaService extends BaseService<MateriaRepository, Materia, MateriaDefaultDTO>{

    private final MateriaConverter converter;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository, MateriaConverter converter) {
        super(materiaRepository);
        this.converter = converter;
    }

    public MateriaDefaultDTO findMateriaById(UUID id) {
        var materia = this.findById(id)
                .orElseThrow(() -> new NotFoundException("Ops, Materia não encontarda para o id informado"));

        return new MateriaDefaultDTO(materia);
    }

    //TODO Validar se tem id, para remover
    public MateriaDefaultDTO saveMateria(MateriaDefaultDTO materiaDefaultDTO) {
        var materia = this.converter.convert(materiaDefaultDTO);

        return this.save(materia);
    }

    public MateriaDefaultDTO updateMateria(MateriaDefaultDTO materiaDefaultDTO) {
        var materia = this.converter.convert(materiaDefaultDTO);

        if (Objects.isNull(materiaDefaultDTO.getIdMateria()) || materiaDefaultDTO.getIdMateria().toString().isEmpty())
            throw new BadRequestException("O id é necessario!");

        this.findMateriaById(materiaDefaultDTO.getIdMateria());
        return this.save(materia);
    }

    public void deleteMateria(UUID id) {
        this.findMateriaById(id);
        this.deleteById(id);
    }

}
