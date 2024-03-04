package lk.ijse.service.custom.impl;

import lk.ijse.config.SessionFactoryConfig;
import lk.ijse.dto.BookDTO;
import lk.ijse.dto.BranchDTO;
import lk.ijse.entity.Book;
import lk.ijse.entity.Branch;
import lk.ijse.repository.RepositoryFactory;
import lk.ijse.repository.custom.BranchRepository;
import lk.ijse.service.custom.BranchService;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class BranchServiceImpl implements BranchService {

    private Session session;

    BranchRepository branchRepository = (BranchRepository) RepositoryFactory.getRepositoryFactory().getRepository(RepositoryFactory.RepositoryTypes.BRANCH);

    @Override
    public List<BranchDTO> getAllBranches() {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        branchRepository.setSession(session);
        List<BranchDTO> branchDTOS = new ArrayList<>();

        for (Branch branch : branchRepository.getAll()){
            branchDTOS.add(new BranchDTO(
                    branch.getBranchId(),
                    branch.getBranchName()
            ));
        }
        transaction.commit();
        session.close();
        return branchDTOS;
    }

    @Override
    public boolean addBranch(BranchDTO branchDTO) {
        session = SessionFactoryConfig.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        branchRepository.setSession(session);
        boolean save = branchRepository.save(branchDTO.toEntity());
        transaction.commit();
        session.close();
        return save;
    }
}
