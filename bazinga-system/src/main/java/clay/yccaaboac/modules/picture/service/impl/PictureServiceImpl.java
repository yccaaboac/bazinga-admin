package clay.yccaaboac.modules.picture.service.impl;

import clay.yccaaboac.modules.picture.domain.Picture;
import clay.yccaaboac.modules.picture.repository.PictureRepository;
import clay.yccaaboac.modules.picture.service.PictureService;
import clay.yccaaboac.modules.picture.service.dto.PictureQueryCriteria;
import clay.yccaaboac.modules.picture.service.mapstruct.PictureMapper;
import clay.yccaaboac.utils.PageUtil;
import clay.yccaaboac.utils.QueryHelp;
import clay.yccaaboac.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    private final PictureMapper pictureMapper;

    @Override
    public Object queryAll(PictureQueryCriteria criteria, Pageable pageable) {
        Page<Picture> page = pictureRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(pictureMapper::toDto));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> ids) {
        pictureRepository.deleteAllByIdIn(ids);
    }

    @Override
    public void create(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void update(Picture resources) {
        Picture picture = pictureRepository.findById(resources.getId()).orElseGet(Picture::new);
        ValidationUtil.isNull(picture.getId(), "Picture", "id", resources.getId());
        picture.setName(resources.getName());
        picture.setPicCategory(resources.getPicCategory());
        picture.setPictureUrl(resources.getPictureUrl());
        pictureRepository.save(picture);
    }

}
