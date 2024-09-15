//package com.trackAgile.filter;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.locationtech.jts.geom.Coordinate;
//import org.locationtech.jts.geom.GeometryFactory;
//import org.locationtech.jts.geom.Point;
//import jakarta.persistence.AttributeConverter;
//import jakarta.persistence.Converter;
//
//@Converter(autoApply = true)
//public class PointListConverter implements AttributeConverter<List<Point>, String> {
//
//	private static final String POINT_DELIMITER = ";";
//	private static final String COORDINATE_DELIMITER = ",";
//
//	private final GeometryFactory geometryFactory = new GeometryFactory();
//
//	@Override
//	public String convertToDatabaseColumn(List<Point> attribute) {
//		if (attribute == null || attribute.isEmpty()) {
//			return "";
//		}
//		return attribute.stream().map(point -> point.getX() + COORDINATE_DELIMITER + point.getY())
//				.collect(Collectors.joining(POINT_DELIMITER));
//	}
//
//	@Override
//	public List<Point> convertToEntityAttribute(String dbData) {
//		if (dbData == null || dbData.isEmpty()) {
//			return Arrays.asList();
//		}
//		return Arrays.stream(dbData.split(POINT_DELIMITER)).map(this::stringToPoint).collect(Collectors.toList());
//	}
//
//	private Point stringToPoint(String pointString) {
//		String[] parts = pointString.split(COORDINATE_DELIMITER);
//		double x = Double.parseDouble(parts[0]);
//		double y = Double.parseDouble(parts[1]);
//		return geometryFactory.createPoint(new Coordinate(x, y));
//	}
//}
