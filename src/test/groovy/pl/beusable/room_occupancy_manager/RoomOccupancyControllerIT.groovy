package pl.beusable.room_occupancy_manager

import org.springframework.http.MediaType
import pl.beusable.room_occupancy_manager.endpoint.model.CalculateRoomOccupancyRequest
import pl.beusable.room_occupancy_manager.endpoint.model.CalculateRoomOccupancyResponse

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post

class RoomOccupancyControllerIT extends IntegrationTest {

    def "should calculate room occupancy"() {
        when:
            def result = mockMvc.perform(post("/api/room-occupancy")
                    .content(objectMapper.writeValueAsString(new CalculateRoomOccupancyRequest(numberOfPremiumRooms, numberOfEconomyRoms)))
                    .contentType(MediaType.APPLICATION_JSON))
            .andReturn().getResponse()
        then:
            def response = objectMapper
                    .readValue(result.getContentAsString(), CalculateRoomOccupancyResponse.class)
            result.status == 200
            response.premiumRoomCount() == expectedPremiumRoomCount
            response.economyRoomCount() == expectedEconomyRoomCount
            response.premiumRoomPrice() == expectedPremiumRoomPrice
            response.economyRoomPrice() == expectedEconomyRoomPrice
        where:
        numberOfPremiumRooms    | numberOfEconomyRoms   | expectedPremiumRoomCount  | expectedEconomyRoomCount  | expectedPremiumRoomPrice  | expectedEconomyRoomPrice
        3                       |   3                   | 3                         | 3                         |  BigDecimal.valueOf(738)  | BigDecimal.valueOf(167.99)
        7                       |   5                   | 6                         | 4                         |  BigDecimal.valueOf(1054)  | BigDecimal.valueOf(189.99)
        2                       |   7                   | 2                         | 4                         |  BigDecimal.valueOf(583)  | BigDecimal.valueOf(189.99)
        7                       |   1                   | 7                         | 1                         |  BigDecimal.valueOf(1153.99)  | BigDecimal.valueOf(45)

    }

}
