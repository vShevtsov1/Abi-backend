package com.project.photoservice.photo.services;

import com.project.companyservice.company.data.Company;
import com.project.photoservice.feigns.CompanyFeign;
import com.project.photoservice.feigns.userFeign;
import com.project.photoservice.photo.data.DTO.projectsResponseDTO;
import com.project.photoservice.photo.data.photo;
import com.project.projectservice.projects.data.DTO.ProjectUploadDTO;
import com.project.projectservice.projects.data.DTO.projectDTO;
import com.project.projectservice.projects.data.projects;
import com.project.projectservice.projects.data.projectsSmall;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoService {
    @Autowired
    private DigitalOceanSpacesUploader spacesUploader;
    @Autowired
    private photoRepo photoRepo;

    @Autowired
    private CompanyFeign companyFeign;
    @Autowired
    private userFeign userFeign;

    public String uploadPhoto(MultipartFile file) {
        return spacesUploader.uploadAndReturnUrl(file);
    }

    public void uploadCompanyLogo(String company, MultipartFile logoFile) {
      String link =  spacesUploader.uploadLogoAndReturnUrl(company,logoFile);
        photo newPhoto = new photo("https://files.abiproptech.info/company_logos/"+link,"https://files.abiproptech.info/company_logos/"+link);
        photoRepo.save(newPhoto);

        companyFeign.updateCompany(newPhoto.get_id(),company);
    }

    public void uploadUserAvatar(String email, MultipartFile avatar) {
        String link =  spacesUploader.uploadAvatarAndReturnUrl(avatar);
        photo newPhoto = new photo("https://files.abiproptech.info/user_avatars/"+link,"https://files.abiproptech.info/user_avatars/"+link);
        photoRepo.save(newPhoto);

        userFeign.setUserAvatar(newPhoto.get_id(),email);
    }
    public Object getPhotoById(String id){
        return photoRepo.findById(id);
    }

    public photo uploadProjectsData(MultipartFile file,String projectId){
        photo url = spacesUploader.uploadProjectAndReturnUrl(projectId,file);
        photoRepo.save(url);
        return url;
    }
}
