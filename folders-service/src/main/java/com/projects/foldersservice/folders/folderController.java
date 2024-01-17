package com.projects.foldersservice.folders;

import com.projects.foldersservice.folders.data.DTO.folderContent;
import com.projects.foldersservice.folders.data.folder;
import com.projects.foldersservice.folders.services.folderService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class folderController {

    @Autowired
    private folderService folderService;

    @PostMapping("/create-folder")
    public ResponseEntity<?> createNewFolder(@RequestParam("folder_name") String folderName, Authentication authentication){
        try {
            folder folder = folderService.createFolder(folderName,authentication.getPrincipal().toString());

            return new ResponseEntity<>(folder,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/my-folders")
    public ResponseEntity<?> getMyFolders( Authentication authentication){
        try {
            List<folder> folder = folderService.getMyFolders(authentication.getPrincipal().toString());

            return new ResponseEntity<>(folder,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/folder-content")
    public ResponseEntity<folderContent> getFolderContent(@RequestParam String folderId) {
        try {
            folderContent folderContent = folderService.getFolderContent(folderId);
            if (folderContent != null) {
                return new ResponseEntity<>(folderContent, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/add-projects")
    public ResponseEntity<?> getMyTeamProjects(@RequestParam String folderId,@RequestBody List<String> projects){
        try {
            folderService.addProjects(folderId,projects);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete-folder")
    public ResponseEntity<?> deleteMyFolder(@RequestParam String folderId){
        try {
            folderService.deleteFolder(folderId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
