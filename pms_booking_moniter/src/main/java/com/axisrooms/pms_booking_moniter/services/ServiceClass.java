package com.axisrooms.pms_booking_moniter.services;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.axisrooms.pms_booking_moniter.models.LogsPmsBookings;
import com.axisrooms.pms_booking_moniter.models.PmsPushBooking;
import com.axisrooms.pms_booking_moniter.repositories.ChannelOtaRepository;
import com.axisrooms.pms_booking_moniter.repositories.LogsPmsBookingsRepository;
import com.axisrooms.pms_booking_moniter.repositories.PmsPushBookingRepository;
import com.axisrooms.pms_booking_moniter.repositories.PmsRepository;
import com.elasticemail.app.API;
import com.elasticemail.app.ApiTypes;
import com.elasticemail.app.FileData;
import com.elasticemail.app.functions.Email;

@Service
public class ServiceClass {

    @Autowired
    PmsPushBookingRepository pushBookingRepository;

    @Autowired
    LogsPmsBookingsRepository logsPmsRepository;

    @Autowired
    PmsRepository pmsRepository;

    @Autowired
    ChannelOtaRepository otaRepository;

    public void returnAllFailedResults(String date) {
	if (date == null) {
	    DateTimeFormatter dateNow = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDateTime now = LocalDateTime.now();
	    date = dateNow.format(now).toString();
	}
	List<PmsPushBooking> pmsFailedBookingslist = pushBookingRepository.getAllTheFailedBookingsUsingDate(date);
	List<String[]> list = createDataSet(pmsFailedBookingslist);
	String fileName = getFileName(date);
	String directory = "\\home\\";
	try {
	    writeToCsvFile(list, directory, fileName);
	    try {
		attachEmail(directory, fileName);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public List<String[]> createDataSet(List<PmsPushBooking> pmsFailedBookingslist) {
	List<String[]> list = new ArrayList<String[]>();
	for (PmsPushBooking pmsBookingEntry : pmsFailedBookingslist) {
	    Integer channelId = (Integer) pmsBookingEntry.getChannelId();
	    // System.out.println(pmsBookingEntry.getBookingNo());
	    String channelName = (otaRepository.getNameById(channelId) == null)
		    ? String.valueOf(pmsBookingEntry.getChannelId())
		    : otaRepository.getNameById(channelId);
	    String bookingIdPms = pmsBookingEntry.getBookingNo();
	    List<LogsPmsBookings> logPmsEntry = logsPmsRepository.findByAccessKey(bookingIdPms);
	    for (LogsPmsBookings obj : logPmsEntry) {
		Integer pmsId = obj.getPmsId();
		String pmsName = (pmsRepository.getPmsNameById(pmsId) == null) ? String.valueOf(obj.getPmsId())
			: pmsRepository.getPmsNameById(pmsId);
		String[] array = { pmsBookingEntry.getBookingNo(), channelName, pmsName, obj.getResponse(),
			obj.getRequest() };
		list.add(array);
	    }
	}
	return list;
    }

    public String getFileName(String date) {
	String fileName = "";
	if (date == null) {
	    DateTimeFormatter dateNow = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    LocalDateTime now = LocalDateTime.now();
	    date = dateNow.format(now).toString();
	}
	fileName = date + "_Failed_Bookings.csv";
	return fileName;
    }

    public void writeToCsvFile(List<String[]> list, String directory, String fileName) throws IOException {
	File f = new File(directory, fileName);
	if (!f.exists())
	    f.createNewFile();

	PrintWriter writer = new PrintWriter(f);
	CSVPrinter printer = CSVFormat.DEFAULT.withHeader("Booking ID", "Channel", "PMS", "Response", "Request")
		.print(writer);
	for (String[] segment : list) {
	    printer.printRecord(segment);
	}
	printer.flush();
    }

    public String attachEmail(String directory, String fileName) throws Exception {
	API.API_KEY = "f21823e3-bcff-43f5-9aec-8f4a2af2c9cc";
	Email email = new Email();
	ApiTypes.EmailSend response;
	ApiTypes.StringArray toSend = new ApiTypes.StringArray();
	// toSend.add();//qa@axisrooms.com
	toSend.add("manish@axisrooms.com");

	List<FileData> fileDataList = new ArrayList<>();
	FileData fileData = FileData.CreateFromFile(directory + "\\" + fileName);
	fileDataList.add(fileData);

	String emailBody = "Hi,Please find the attachment for failed bookings.";
	response = email.send("The Failed PMS Bookings", "noreply@axisrooms.com", "Failed Bookings for Todays Date", "",
		"", "", "", "", "", toSend, null, null, null, null, null, "", "", emailBody, "", "", "", "",
		ApiTypes.EncodingType.BASE64, "", fileDataList, null, "", null, "", "", false);

	if (response != null)
	    return "Email successfully sent";
	return "Email isn't sent";
    }

}
