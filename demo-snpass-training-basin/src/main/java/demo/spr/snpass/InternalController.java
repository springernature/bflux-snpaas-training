package demo.spr.snpass;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class. This is written for Cloud foundry. Cloud foundry needs this api for making the
 * application up It checks for these three apis those should return OK.
 */
@RestController
@RequestMapping("/internal")
public class InternalController {

    private static final String INTERNAL_STATUS = "/status";

    private static final String INTERNAL_VERSION = "/version";

    private static final String INTERNAL_CONFIG = "/config";

    private static final String INTERNAL_DEPENDENCIES = "/dependencies";

    /**
     * @return String[]
     */
    @RequestMapping(method = GET)
    public String[] getInternal() {
        return new String[] {
                INTERNAL_STATUS,
                INTERNAL_VERSION,
                INTERNAL_CONFIG,
                INTERNAL_DEPENDENCIES };
    }

    /**
     * @return ResponseEntity
     */
    @RequestMapping(
            value = INTERNAL_STATUS,
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> status() {
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * @return ResponseEntity
     */
    @RequestMapping(value = INTERNAL_VERSION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VersionInfo> version() {
        return new ResponseEntity<>(new VersionInfo(), HttpStatus.OK);
    }

    /**
     * @return ResponseEntity
     */
    @RequestMapping(value = INTERNAL_CONFIG, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> config() {
        final String config = "{\"status\":200}";
        return new ResponseEntity<>(config, HttpStatus.OK);
    }

    /**
     * @return ResponseEntity
     */
    @RequestMapping(value = INTERNAL_DEPENDENCIES, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> dependencies() {
        final String config = "{\"status\":200}";
        return new ResponseEntity<>(config, HttpStatus.OK);
    }

    /**
     * @author Prashant Shinde
     */
    class VersionInfo {

        private final String revision = System.getenv("GIT_REVISION");

        /**
         * @return String
         */
        public String getRevision() {
            return revision;
        }

    }
}
