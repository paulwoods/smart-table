package org.mrpaulwoods.filtering.search;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class SearchService {

    public <T> SmartResult<T> createSpecification(
            JpaSpecificationExecutor<T> repository,
            List<SmartColumn> smartColumns,
            Pageable pageable,
            List<String> filters
    ) {
        SmartResult<T> result = new SmartResult<>();
        result.addColumns(smartColumns);
        Specification<T> specification = createSpecification(filters);
        result.setPage(repository.findAll(specification, pageable));
        return result;
    }

//    public <T> SmartResult<T> searchWithCriteria(
//            JpaSpecificationExecutor<T> repository,
//            List<SmartColumn> smartColumns,
//            Pageable pageable,
//            List<String> filters,
//            List<SmartListCriteria> listCriteria
//    ) {
//        log.debug("search(pageable:{}, filters:{}, criteria:{})", pageable, filters, listCriteria);
//
//        SmartResult<T> result = new SmartResult<>();
//        result.addColumns(smartColumns);
//
//        long start = System.currentTimeMillis();
//        result.setPage(repository.findAll(search(filters, getCriteriaSpec(listCriteria)), pageable));
//        log.warn("### smart table query: completed in {} ms", System.currentTimeMillis() - start);
//
//        return result;
//    }
//
//    public <T> Specification<T> search(List<String> filters, Specification<T> listCriteria) {
//
//        Specification<T> spec = search(filters);
//
//        if ( spec == null ){
//            spec = listCriteria;
//        }else{
//            spec = spec.and(listCriteria);
//        }
//
//        return spec;
//    }

//    private <T> Specification<T> prepareCriteriaSpec(SmartListCriteria criteria) {
//        return (root, query, criteriaBuilder)->
//                criteriaBuilder.in(root.get(criteria.getColumnName())).value(criteria.getListFilterValue());
//    }
//
//    public <T> Specification<T> getCriteriaSpec(List<SmartListCriteria> listCriteria) {
//
//        Specification<T> criteriaSpec = null;
//        if ( listCriteria == null || listCriteria.isEmpty() ) return criteriaSpec;
//
//        for ( SmartListCriteria slc : listCriteria){
//            if ( criteriaSpec == null ){
//                criteriaSpec = prepareCriteriaSpec(slc);
//            }else{
//                criteriaSpec.and(prepareCriteriaSpec(slc));
//            }
//        }
//
//        return criteriaSpec;
//
//    }


    public <T> Specification<T> createSpecification(List<String> filters) {

        if (null == filters || filters.isEmpty()) {
            return null;
        }

        List<SmartSpecification<T>> specs = filters.stream()
                .map(SmartFilter::build)
                .filter(Objects::nonNull)
                .map(SmartSpecification<T>::new)
                .toList();

        Specification<T> spec = null;

        for (SmartSpecification<T> s : specs) {
            if (spec == null) {
                spec = s;
            } else {
                spec = spec.and(s);
            }
        }

        return spec;
    }

}


