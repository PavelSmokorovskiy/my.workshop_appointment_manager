package wam.controller;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wam.config.Logging;
import wam.model.ResponseDescription;
import wam.model.Workshop;
import wam.repository.WorkshopRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * REST controller for Workshop Entity
 */
@RestController
@RequestMapping(value = "/workshop", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkshopController {

    private final Logging logger = new Logging(LogManager.getLogger(this.getClass()));

    @Autowired
    private WorkshopRepository workshopRepository;

    /**
     * Get total number of workshops
     *
     * @link localhost:8080/workshop/total
     */
    @GetMapping(value = "/total")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Long total() {
        return workshopRepository.count();
    }

    /**
     * Get all workshops
     *
     * @link http://localhost:8080/workshop/
     */
    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Workshop> getAllWorkshops(HttpServletRequest request) {

        logger.requestStart(request);

        List<Workshop> workshops = workshopRepository.findAll();

        logger.requestStop(workshops);
        return workshops;
    }

    /**
     * Get a workshop by id
     *
     * @link localhost:8080/workshop/id/1
     */
    @GetMapping("/id/{id}")
    @ResponseBody
    public ResponseEntity getIdWorkshop(@PathVariable("id") final Long id, HttpServletRequest request) {

        logger.requestStart(request);

        Workshop workshop = workshopRepository.findByWorkshopId(id);

        logger.requestStop(workshop);
        return new ResponseEntity<>(workshop, HttpStatus.OK);
    }

    /**
     * Create a workshop
     *
     * @link http://localhost:8080/workshop/companyName/companyName/trademarks/trademarks/city/city/postalCode/postalCode/country/country
     */
    @PostMapping("/companyName/{companyName}/trademarks/{trademarks}/city/{city}/postalCode/{postalCode}/country/{country}")
    @ResponseBody
    public ResponseEntity createWorkshop(
            @PathVariable("companyName") final String companyName
            , @PathVariable("trademarks") final String trademarks
            , @PathVariable("city") final String city
            , @PathVariable("postalCode") final String postalCode
            , @PathVariable("country") final String country
            , HttpServletRequest request) {

        logger.requestStart(request);

        try {

            Workshop workshop = new Workshop(
                    companyName
                    , trademarks
                    , city
                    , postalCode
                    , country);
            workshopRepository.save(workshop);

            logger.requestStop(workshop);
            return new ResponseEntity<>(workshop, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Didn't create")
                    , HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Create a user from front
     */
    @PostMapping(value = "/save")
    @ResponseBody
    public String saveUser(@RequestBody Workshop workshop) {
        workshopRepository.save(workshop);
        return workshop.getWorkshopId().toString();
    }

    /**
     * Update a workshop data
     *
     * @link localhost:8080/workshop/companyName/companyName/trademarks/trademarks/city/city/postalCode/postalCode/country/country
     */
    @PutMapping(value = "/id/{id}/companyName/{companyName}/trademarks/{trademarks}/city/{city}/postalCode/{postalCode}/country/{country}"
            , produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity updateWorkshop(
            @PathVariable("id") final Long id
            , @PathVariable("companyName") final String companyName
            , @PathVariable("trademarks") final String trademarks
            , @PathVariable("city") final String city
            , @PathVariable("postalCode") final String postalCode
            , @PathVariable("country") final String country
            , HttpServletRequest request) {

        logger.requestStart(request);

        try {

            Workshop workshop = workshopRepository.findByWorkshopId(id);
            workshop.setCompanyName(companyName);
            workshop.setTrademarks(trademarks);
            workshop.setCity(city);
            workshop.setPostalCode(postalCode);
            workshop.setCountry(country);
            workshopRepository.save(workshop);

            logger.requestStop();
            return new ResponseEntity<>(workshop, HttpStatus.CREATED);

        } catch (Exception e) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Didn't update")
                    , HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * Delete a workshop
     *
     * @link localhost:8080/workshop/id/1
     */
    @DeleteMapping(value = "/id/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteWorkshop(@PathVariable("id") final Long id, HttpServletRequest request) {

        logger.requestStart(request);

        Workshop workshop = workshopRepository.findByWorkshopId(id);

        if (workshop == null) {

            logger.requestStop();
            return new ResponseEntity<>(new ResponseDescription("Workshop with id "
                    + id + " was not found."), HttpStatus.NOT_FOUND);
        }

        workshopRepository.deleteById(id);
        logger.requestStop();
        return new ResponseEntity<>(new ResponseDescription("Workshop with id "
                + id + " was deleted. " + total() + " Workshops total"), HttpStatus.OK);
    }

    /**
     * Delete all workshops
     *
     * @link localhost:8080/Workshop/
     */
    @DeleteMapping(value = "/")
    @ResponseBody
    public ResponseEntity<?> deleteAllWorkshops(HttpServletRequest request) {

        logger.requestStart(request);

        workshopRepository.deleteAll();
        logger.requestStop();
        return new ResponseEntity<>(new ResponseDescription("All Workshops was deleted.")
                , HttpStatus.OK);
    }

    /**
     * Filter a workshop by companyName
     *
     * @link localhost:8080/workshop/filter/companyName/companyName
     */
    @GetMapping("/filter/companyName/{companyName}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Workshop> getCompanyNameFilterWorkshop(@PathVariable("companyName") final String companyName, HttpServletRequest request) {

        logger.requestStart(request);

        List<Workshop> workshops = workshopRepository.findByCompanyNameContains(companyName);

        logger.requestStop(workshops);
        return workshops;
    }

    /**
     * Filter a workshop by city
     *
     * @link localhost:8080/workshop/filter/city/city
     */
    @GetMapping("/filter/city/{city}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Workshop> getCityFilterWorkshop(@PathVariable("city") final String city, HttpServletRequest request) {

        logger.requestStart(request);

        List<Workshop> workshops = workshopRepository.findByCityContains(city);

        logger.requestStop(workshops);
        return workshops;
    }
}
