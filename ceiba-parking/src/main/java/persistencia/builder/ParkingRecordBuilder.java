package persistencia.builder;

import com.ceiba.domain.ParkingRecord;
import com.ceiba.persistence.entity.ParkingRecordEntity;

public class ParkingRecordBuilder {

	private ParkingRecordBuilder() {
	}

	public static ParkingRecord convertirADominio(ParkingRecordEntity parkingRecordEntity) {

		ParkingRecord parkingRecord = null;

		if (parkingRecordEntity != null) {
			parkingRecord = new ParkingRecord(parkingRecordEntity.getLicensePlate(),
					parkingRecordEntity.getEngineCapacity(), parkingRecordEntity.getInitialDate(),
					parkingRecordEntity.getFinalDate(), parkingRecordEntity.getVehicleType(),
					parkingRecordEntity.isParkingBusy(), parkingRecordEntity.getParkingValue());
		}

		return parkingRecord;
	}

	public static ParkingRecordEntity convertirAEntity(ParkingRecord parkingRecord) {

		ParkingRecordEntity parkingRecordEntity = new ParkingRecordEntity();

		parkingRecordEntity.setLicensePlate(parkingRecord.getLicensePlate());
		parkingRecordEntity.setEngineCapacity(parkingRecord.getEngineCapacity());
		parkingRecordEntity.setInitialDate(parkingRecord.getInitialDate());
		parkingRecordEntity.setFinalDate(parkingRecord.getFinalDate());
		parkingRecordEntity.setVehicleType(parkingRecord.getVehicleType());
		parkingRecordEntity.setParkingBusy(parkingRecord.isParkingBusy());
		parkingRecordEntity.setParkingValue(parkingRecord.getParkingValue());

		return parkingRecordEntity;
	}
}
