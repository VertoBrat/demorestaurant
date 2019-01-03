package ru.photorex.demorestaurant;

public class HttpApiTestsUtil {

    public static String getUserForRegister(boolean isAdmin) {
        return !isAdmin ? "{\n" +
                "\t\"userName\":\"user1\",\n" +
                "\t\"password\":\"user1\",\n" +
                "\t\"email\":\"user1@mail.ru\"\n" +
                "}" :
                "{\n" +
                        "\t\"userName\":\"admin1\",\n" +
                        "\t\"password\":\"admin1\",\n" +
                        "\t\"email\":\"admin1@mail.ru\"\n" +
                        "}";
    }

    public static String getResponseRestaurantsWithoutAuth() {
        return "{\n" +
                "    \"_embedded\": {\n" +
                "        \"restaurants\": [\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"cake\",\n" +
                "                        \"price\": 25\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\": \"juice\",\n" +
                "                        \"price\": 15\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace2\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"corn\",\n" +
                "                        \"price\": 25\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\": \"soup\",\n" +
                "                        \"price\": 30\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace4\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"eggs\",\n" +
                "                        \"price\": 5\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"name\": \"porridge\",\n" +
                "                        \"price\": 15\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace5\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"cake\",\n" +
                "                        \"price\": 25\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace6\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"juice\",\n" +
                "                        \"price\": 15\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace7\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"corn\",\n" +
                "                        \"price\": 25\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace8\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"soup\",\n" +
                "                        \"price\": 30\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace9\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"cutlet\",\n" +
                "                        \"price\": 50\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace10\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"eggs\",\n" +
                "                        \"price\": 5\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace11\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"porridge\",\n" +
                "                        \"price\": 15\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace12\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"cake\",\n" +
                "                        \"price\": 25\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace13\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"juice\",\n" +
                "                        \"price\": 15\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace14\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"corn\",\n" +
                "                        \"price\": 25\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace15\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"soup\",\n" +
                "                        \"price\": 30\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace16\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"cutlet\",\n" +
                "                        \"price\": 50\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace17\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"eggs\",\n" +
                "                        \"price\": 5\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace18\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"porridge\",\n" +
                "                        \"price\": 15\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace19\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"cake\",\n" +
                "                        \"price\": 25\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace20\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"juice\",\n" +
                "                        \"price\": 15\n" +
                "                    }\n" +
                "                ]\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace21\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"dishes\": [\n" +
                "                    {\n" +
                "                        \"name\": \"corn\",\n" +
                "                        \"price\": 25\n" +
                "                    }\n" +
                "                ]\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"_links\": {\n" +
                "        \"first\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants?page=0&size=20\"\n" +
                "        },\n" +
                "        \"self\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants?page=0&size=20\"\n" +
                "        },\n" +
                "        \"next\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants?page=1&size=20\"\n" +
                "        },\n" +
                "        \"last\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants?page=1&size=20\"\n" +
                "        },\n" +
                "        \"register-new-user\": {\n" +
                "            \"href\": \"http://localhost:8080/api/registration\"\n" +
                "        }\n" +
                "    },\n" +
                "    \"page\": {\n" +
                "        \"size\": 20,\n" +
                "        \"totalElements\": 23,\n" +
                "        \"totalPages\": 2,\n" +
                "        \"number\": 0\n" +
                "    }\n" +
                "}";
    }

    public static String getForAdmin() {
        return "{\n" +
                "    \"_embedded\": {\n" +
                "        \"restaurants\": [\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/1\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace2\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/2\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace3\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/3\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace4\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/4\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace5\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/5\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace6\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/6\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace7\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/7\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace8\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/8\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace9\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/9\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace10\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/10\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace11\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/11\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace12\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/12\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace13\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/13\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace14\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/14\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace15\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/15\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace16\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/16\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace17\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/17\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace18\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/18\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace19\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/19\"\n" +
                "                    }\n" +
                "                }\n" +
                "            },\n" +
                "            {\n" +
                "                \"name\": \"GrandPalace20\",\n" +
                "                \"location\": \"DownTown\",\n" +
                "                \"voteRank\": 0,\n" +
                "                \"_links\": {\n" +
                "                    \"self\": {\n" +
                "                        \"href\": \"http://localhost:8080/api/restaurants/20\"\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        ]\n" +
                "    },\n" +
                "    \"_links\": {\n" +
                "        \"first\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/all?page=0&size=20\"\n" +
                "        },\n" +
                "        \"self\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/all?page=0&size=20\"\n" +
                "        },\n" +
                "        \"next\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/all?page=1&size=20\"\n" +
                "        },\n" +
                "        \"last\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/all?page=1&size=20\"\n" +
                "        },\n" +
                "        \"add\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants\"\n" +
                "        },\n" +
                "        \"delete\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/{id}\",\n" +
                "            \"templated\": true\n" +
                "        },\n" +
                "        \"update\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/{id}\",\n" +
                "            \"templated\": true\n" +
                "        }\n" +
                "    },\n" +
                "    \"page\": {\n" +
                "        \"size\": 20,\n" +
                "        \"totalElements\": 24,\n" +
                "        \"totalPages\": 2,\n" +
                "        \"number\": 0\n" +
                "    }\n" +
                "}";
    }

    public static String getOneRestaurantForAdmin() {
        return "{\n" +
                "    \"name\": \"GrandPalace\",\n" +
                "    \"location\": \"DownTown\",\n" +
                "    \"voteRank\": 0,\n" +
                "    \"dishes\": [\n" +
                "        {\n" +
                "            \"name\": \"cake\",\n" +
                "            \"price\": 25,\n" +
                "            \"_links\": {\n" +
                "                \"self\": {\n" +
                "                    \"href\": \"http://localhost:8080/api/dishes/1\"\n" +
                "                }\n" +
                "            }\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"juice\",\n" +
                "            \"price\": 15,\n" +
                "            \"_links\": {\n" +
                "                \"self\": {\n" +
                "                    \"href\": \"http://localhost:8080/api/dishes/2\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ],\n" +
                "    \"_links\": {\n" +
                "        \"self\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/1\"\n" +
                "        },\n" +
                "        \"add-dish\": {\n" +
                "            \"href\": \"http://localhost:8080/api/dishes/1\"\n" +
                "        },\n" +
                "        \"delete\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/1\"\n" +
                "        },\n" +
                "        \"update\": {\n" +
                "            \"href\": \"http://localhost:8080/api/restaurants/1\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    public static String getNewDish() {
        return "{\n" +
                "\t\"name\":\"update\",\n" +
                "\t\"price\":1000,\n" +
                "\t\"restaurant\":2\n" +
                "}";
    }

    public static String getNewRestaurant() {
        return "{\n" +
                "\t\"name\":\"New\",\n" +
                "\t\"location\":\"ChineMall\"\n" +
                "}";
    }

    public static String getUpdatedRestaurant() {
        return "{\n" +
                "\t\"name\":\"Updated\"\n" +
                "}";
    }
}
