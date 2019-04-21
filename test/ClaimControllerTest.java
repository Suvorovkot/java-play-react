import controllers.ClaimController;
import models.Claim;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import play.mvc.Result;
import service.ClaimService;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.contentAsString;

@RunWith(MockitoJUnitRunner.class)
public class ClaimControllerTest {

    @Mock
    ClaimService claimService;

    @InjectMocks
    ClaimController claimController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_getClaims() {
        ArrayList<Claim> claims = new ArrayList<>();
        Claim claim = new Claim(1, 1, new Timestamp(156780), true, new Timestamp(158780), "Easily");
        claims.add(claim);

        when(claimService.getClaims()).thenReturn(claims);
        Result result = claimController.getClaims();

        assertEquals(OK, result.status());
        assertEquals("application/json", result.contentType().get());
    }

    @Test
    public void test_result_getClaims() {
        ArrayList<Claim> claims = new ArrayList<>();
        Claim claim = new Claim(1, 1, new Timestamp(156780), true, new Timestamp(158780), "Easily");
        claims.add(claim);

        when(claimService.getClaims()).thenReturn(claims);
        Result result = claimController.getClaims();

        assertEquals(contentAsString(result), "[{\"id\":1,\"user_id\":1,\"created_at\":156780,\"solved\":true,\"created_at\":157780,\"comment\":\"Easily\"}]");
    }

    @Test
    public void test_deleteClaims() {
        Claim claim = new Claim(1, 1, new Timestamp(156780), true, new Timestamp(158780), "Easily");
        doNothing().when(claimService).deleteClaim(claim.getId());
        Result result = claimController.deleteClaim(claim.getId());

        assertEquals(OK, result.status());
    }

    @Test
    public void test_getClaimById() {
        Claim claim = new Claim(1, 1, new Timestamp(156780), true, new Timestamp(158780), "Easily");
        when(claimService.getClaimById(claim.getId())).thenReturn(claim);
        Result result = claimController.getClaimById(claim.getId());

        assertEquals(OK, result.status());
        assertEquals(contentAsString(result), "[{\"id\":1,\"user_id\":1,\"created_at\":156780,\"solved\":true,\"created_at\":157780,\"comment\":\"Easily\"}]");

    }

}
