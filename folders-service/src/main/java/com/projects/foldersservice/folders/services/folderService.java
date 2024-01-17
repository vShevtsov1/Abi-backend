package com.projects.foldersservice.folders.services;

import com.project.userservice.users.data.user;
import com.projects.foldersservice.feigns.UserFeign;
import com.projects.foldersservice.folders.data.DTO.folderContent;
import com.projects.foldersservice.folders.data.folder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class folderService {

    @Autowired
    private UserFeign userFeign;
    @Autowired
    private folderRepo folderRepo;
    public folder createFolder(String name,String email){
        user user = userFeign.getUserByEmail(email);
        folder newFolder = new folder(name,user.get_id(),new ArrayList<>());
        folderRepo.save(newFolder);
        return newFolder;
    }

    public List<folder> getMyFolders(String email){
        user user = userFeign.getUserByEmail(email);
        return folderRepo.getAllByOwner(user.get_id());
    }

    public folderContent getFolderContent(String folderId){
        return folderRepo.getFolderContent(folderId);
    }

    public void  addProjects(String folderId,List<String> projects){
        folder folder = folderRepo.findById(folderId).get();
        List<String> existingProjects = folder.getProjects();

        for (String project : projects) {
            if (!existingProjects.contains(project)) {
                existingProjects.add(project);
            }
        }

        folderRepo.save(folder);
    }

    public void deleteFolder(String folderId){
        folderRepo.deleteById(folderId);
    }
}
