package com.nhlstenden;

public class Regex {

    public static String moviesList = "(?:\\\"?(?<title>.*?)\\\"?)\\s+?\\((?<year>\\d{4}|(?<unknownYear>[?]{4}))(?:\\/(?<movieNamePerYear>.*?))?\\)\\s(?:\\{(?:(?<seriesEpisodeName>.*?))?(?:\\(\\#(?<seriesSeason>\\d{1,})\\.(?<seriesEpisode>\\d{1,})\\))?\\})?(?:\\((?<mediaType>.{1,2})\\))?(?:\\s+(?<seriesPeriod>(?<startYear>[\\d]{4})\\-(?<endYear>[\\d]{4}|[?]{4})))?(?:\\s\\{(?<suspended>.*\\}))?(?:\\s+(?<yearOfRelease>[\\d]{4}|[?]{4})?)";

    public static String ratingsRegex = "\\s{6}(?<distribution>.{1,10})\\s+(?<votes>\\d{1,7})\\s{2,3}(?<rank>\\d{1,2}\\.\\d)\\s{2}(?:\\\"?(?<title>.*?)\\\"?)\\s\\((?<year>\\d{4}|(?<unknownYear>[?]{4}))(?:\\/(?<movieNamePerYear>.*?)?)?\\)(?:\\s\\{(?:(?<seriesEpisodeName>.*?)\\s?)(?:\\(\\#(?<seriesSeason>\\d{1,})\\.(?<seriesEpisode>\\d{1,})\\))?\\})?(?:\\s\\((?<mediaType>\\w{1,})\\))?";

    public static String runningTimeRegex = "\\\"?(?<title>.*?)\\\"?\\s\\((?<year>\\d{4}|(?<unknownYear>[?]{4}))\\)?(?:\\/(?<movieNamePerYear>.*?)\\))?\\s+(?:\\{(?!\\{)(?:(?<seriesEpisodeName>.*?)\\s?)(?:\\(\\#(?<seriesSeason>\\d{1,})\\.(?<seriesEpisode>\\d{1,})\\))?\\})?\\s?(?:\\((?<mediatype>.{1,2})\\))?\\s?(?:\\{\\{(?<suspended>.*?)\\}\\})?\\s*(?:(?<time>\\d{1,}))?(?:(?<country>.*?)\\:(?:.*?(?<countryTime>\\d{1,})))?(?:(?<extraInformation>.*))?";

}
