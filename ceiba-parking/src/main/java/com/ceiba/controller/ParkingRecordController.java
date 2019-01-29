
@Controller
public class ParkingRecordController {

	@RequestMapping("/")
	@ResponseBody
	String home() {
		return "HolaMundo";
	}

}
