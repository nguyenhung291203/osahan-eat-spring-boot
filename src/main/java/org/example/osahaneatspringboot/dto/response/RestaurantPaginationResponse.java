package org.example.osahaneatspringboot.dto.response;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

import org.example.osahaneatspringboot.dto.request.RestaurantPaginationRequest;
import org.example.osahaneatspringboot.entity.Restaurant;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantPaginationResponse {
    List<RestaurantResponse> content;
    long totalElements;
    long totalPages;
    int currentPage;
    int pageSize;

    public RestaurantPaginationResponse(
            List<Restaurant> allRestaurants,
            RestaurantPaginationRequest request,
            Function<Restaurant, RestaurantResponse> mapper) {
        this.totalElements = allRestaurants.size();
        this.pageSize = request.getLimit();
        this.currentPage = request.getPage();
        this.totalPages = (int) Math.ceil((double) totalElements / pageSize);

        // Nếu trang hiện tại lớn hơn tổng số trang, đặt content thành danh sách rỗng
        if (request.getPage() > this.totalPages) {
            this.content = new ArrayList<>();
            return;
        }

        if ("LOCATION".equals(request.getSortBy()) && request.getLatitude() != null && request.getLongitude() != null) {
            allRestaurants.sort(Comparator.comparingDouble(r -> calculateDistance(
                    request.getLatitude(), request.getLongitude(),
                    r.getLatitude(), r.getLongitude())));
        }

        int start = (this.currentPage - 1) * pageSize;
        int end = Math.min(start + pageSize, allRestaurants.size());
        List<Restaurant> paginatedList = allRestaurants.subList(start, end);

        this.content = paginatedList.stream().map(mapper).toList();
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS = 6371;

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1))
                        * Math.cos(Math.toRadians(lat2))
                        * Math.sin(lonDistance / 2)
                        * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return EARTH_RADIUS * c;
    }
}
