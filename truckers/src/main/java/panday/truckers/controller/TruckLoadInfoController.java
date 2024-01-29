package panday.truckers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import panday.truckers.entity.TruckLoadInfo;
import panday.truckers.repos.ITruckLoadInfoRepo;

import java.lang.reflect.Field;
import java.util.*;

@RestController
public class TruckLoadInfoController {

    @Autowired
    ITruckLoadInfoRepo truckLoadInfoRepo;
    @GetMapping("/api/ping")
    public ResponseEntity<Map<String, Object>> pong() {
        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "PONG");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/api/load")
    public ResponseEntity<Map<String, Object>> addNewLoad(@RequestBody TruckLoadInfo truckLoadInfo) {
        Map<String , Object> response = new HashMap<>();
        response.put("data", truckLoadInfoRepo.save(truckLoadInfo));
        response.put("success", true);
        response.put("message", "Successfully created the Load Item");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/api/load")
    public ResponseEntity<Map<String , Object>> getAllLoads(){
        Map<String , Object> response = new HashMap<>();
        response.put("data", truckLoadInfoRepo.findAll());
        response.put("success", true);
        response.put("message", "Successfully fetched all the Load Item");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/api/load/{id}")
    public ResponseEntity<Map<String, Object>> getLoadById(@PathVariable UUID id){
        Optional<TruckLoadInfo> loadItem = truckLoadInfoRepo.findById(id);
        Map<String, Object> response = new HashMap<>();
        if(loadItem.isPresent()){
            response.put("success", true);
            response.put("data", response);
            response.put("message", "Load item was successfully fetched");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else{
            response.put("success", false);
            response.put("message", "Load item was not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/load/shipper/{shipperId}")
    public ResponseEntity<Map<String, Object>> getLoadById(@PathVariable String shipperId){
        List<TruckLoadInfo> loadItems = truckLoadInfoRepo.findByShipperId(shipperId);
        Map<String, Object> response = new HashMap<>();

        if (!loadItems.isEmpty()) {
            response.put("success", true);
            response.put("data", loadItems);
            response.put("message", "Load items were successfully fetched");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("success", false);
            response.put("message", "No load items found for the specified shipperId");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/load/{id}")
    public ResponseEntity<Map<String, Object>> updateLoadItem(@PathVariable UUID id, @RequestBody TruckLoadInfo updatedLoadDetails) {
        Optional<TruckLoadInfo> loadItem = truckLoadInfoRepo.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (loadItem.isPresent()) {
            TruckLoadInfo existingLoadItem = loadItem.get();

            for (Field field : TruckLoadInfo.class.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    Object updatedValue = field.get(updatedLoadDetails);
                    if (updatedValue != null) {
                        field.set(existingLoadItem, updatedValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            TruckLoadInfo updatedLoadItem = truckLoadInfoRepo.save(existingLoadItem);
            response.put("success", true);
            response.put("data", updatedLoadItem);
            response.put("message", "The load item was successfully updated");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("success", false);
            response.put("message", "The load item is not present");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/api/load/{id}")
    public ResponseEntity<Map<String, Object>> removeLoadItem(@PathVariable UUID id){
        Optional<TruckLoadInfo> studentToBeDeleted = truckLoadInfoRepo.findById(id);
        Map<String, Object> response = new HashMap<>();
        if(studentToBeDeleted.isPresent()){
            truckLoadInfoRepo.deleteById(id);
            response.put("success", true);
            response.put("data", studentToBeDeleted.get());
            response.put("message", "Item successfully deleted");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }else{
            response.put("success", false);
            response.put("message", "Item was not present i.e not deleted");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
