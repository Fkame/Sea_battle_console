import java.util.ArrayList;

public class DotComBust {
	
	// Количество догадок
	int numOfGuesses = 0;
	
	// Приём пользовательского ввода и подбор расположения корабликов
	GameHelper helper = new GameHelper();
	
	// Коллекция кораблей для потопления
	ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
	
	
	// Статическая функция, вызываемая без создания объекта текущего класса
	public static void main (String[] args) {
		
		// Тестирование
		/*
		if (DotComBust.setUpGameTest() == true) System.out.println("Method setUpGame is OK");
		else System.out.println("Method setUpGame is WRONG");
		System.out.println("");
		
		if (DotComBust.checkUserGuessTest() == true) System.out.println("Method checkUserGuess is OK");
		else System.out.println("Method checkUserGuess is WRONG");
		System.out.println("");
		
		if (DotComBust.checkUserGuessTestBySetCells() == true) System.out.println("Method checkUserGuessBySetCells is OK");
		else System.out.println("Method checkUserGuessBySetCells is WRONG");
		System.out.println("");
		*/
		
		System.out.println("\n-------------------------\nLet The Game Begin\n------------------------");
		DotComBust game = new DotComBust();
		game.setUpGame();
		//game.showCells();
		game.startPlaying();
		
	}
	
	
	// Создает 3 объекта DotCom и назначает им имена. Выводит инструкцию
	private void setUpGame() {
		// Создание "сайтов"
		DotCom one = new DotCom();
		one.setName("Pets.com");
		DotCom two = new DotCom();
		two.setName("eToys.com");
		DotCom three = new DotCom();
		three.setName("Go2.com");
		dotComList.add(one);
		dotComList.add(two);
		dotComList.add(three);
		
		System.out.println("Ваша цель - потопить три \"сайта\".");
		System.out.println("Pets.com, eToys.com, Go2.com");
		System.out.println("Попытайтесь сделать это за минимальное количество ходов");
		
		for (DotCom dotComToSet : dotComList) {
			ArrayList<String> newLocation = helper.placeDotCom(3);
			dotComToSet.setLocationCells(newLocation);
		}	
	}
	
	
	// Приём и проверка ввода пользователя пока все корабли не будут потоплены
	private void startPlaying() {
		while (!dotComList.isEmpty()) {
			String userGuess = helper.getUserInput("Сделайте ход");
			checkUserGuess(userGuess);
		}
		finishGame();
	}
	
	
	// Проверка координат на попадание
	private boolean checkUserGuess(String userGuess) {
		numOfGuesses++;
		
		String result = "Мимо";
		
		for (DotCom dotComToTest : dotComList) {
			result = dotComToTest.checkYourself(userGuess);
			if (result.equals("Попал")) {
				break;
			}
			if (result.equals("Потопил")) {
				dotComList.remove(dotComToTest);
				break;
			}
		}
		System.out.println(result);
		
		//for testing
		if (result.equals("Попал") || result.equals("Потопил")) return true;
		else return false;
	}
	
	
	// Сообщение о конце игры и вывод статистики
	private void finishGame() {
		System.out.println("Все \"сайты\" ушли ко дну! Ваши акции теперь ничего не стоят!");
		if (numOfGuesses <= 18) {
			System.out.println("Это заняло у вас всего " + numOfGuesses + " попыткок.");
			System.out.println("Вы успели выбраться до того, как ваши вложения утонули.");
		} else {
			System.out.println("Это заняло у вас довольно много времени. " + numOfGuesses + "попыткок.");
			System.out.println("Рыбы водят хороводы вокруг ваших вложений.");
		}
	}
	
	
	// Геттер
	public ArrayList<DotCom> getDots() {
		return this.dotComList;
	}
	
	
	// Тест 1
	private static boolean setUpGameTest() {
		System.out.println("Test #1");
		DotComBust dotComBust = new DotComBust();
		dotComBust.setUpGame();
		
		ArrayList<DotCom> dotComListTest = dotComBust.getDots();
		
		if (dotComListTest.size() != 3) return false;
		return true;
	}
	
	
	// Тест 2
	private static boolean checkUserGuessTest() {
		System.out.println("Test #2");
		
		// Создание игры и генерация кораблей
		DotComBust dotComBust = new DotComBust();
		dotComBust.setUpGame();
		
		// Получение списка кораблей этой игры
		ArrayList<DotCom> dotComListTest = dotComBust.getDots();
		
		char[] field1 = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		char[] field2 = {'1', '2', '3', '4', '5', '6', '7'};	
		
		for (char value1 : field1) {
			for (char value2 : field2) {
				// Формируем итоговую догадку
				String guess = String.valueOf(value1 + value2);
				
				// Получаем ответ от функции
				boolean testMethodAnswer = dotComBust.checkUserGuess(guess);
				
				// Проверка работы функции путем поиска таких координат в объектах DotCom
				boolean answerFromTest = false;
				
				// Проверка каждого корабля
				for (DotCom dot : dotComListTest) {
					ArrayList<String> dotsLocation = dot.getLocationCells();
					
					// Проверка каждой клетки корабля
					for (String cell : dotsLocation) {
						if (cell == guess) answerFromTest = true;
						break;
					}
					
					if (answerFromTest == true) break;
				}
				
				// Если хоть одна клетка не совпадает - функция работает неправильно
				if (testMethodAnswer != answerFromTest) return false;
			}
		}
		
		return true;
	}


	// Тест 2.5
	private static boolean checkUserGuessTestBySetCells() {
		System.out.println("Test #2.5");
		// Создание игры и генерация кораблей
		DotComBust dotComBust = new DotComBust();
		dotComBust.setUpGame();
		
		// Получение списка кораблей этой игры
		ArrayList<DotCom> dotComListTest = dotComBust.getDots();
		
		// Список новых координат корабля
		ArrayList<String> newLocations1 = new ArrayList<String>();
		newLocations1.add("A1");
		newLocations1.add("A2");
		newLocations1.add("A3");
		String[] input1 = {"A1", "A2", "A3"};
		
		ArrayList<String> newLocations2 = new ArrayList<String>();
		newLocations2.add("B1");
		newLocations2.add("B2");
		newLocations2.add("B3");
		String[] input2 = {"B1", "B2", "B3"};
		
		ArrayList<String> newLocations3 = new ArrayList<String>();
		newLocations3.add("C1");
		newLocations3.add("C2");
		newLocations3.add("C3");
		String[] input3 = {"C1", "C2", "C3"};
		
		// Установка новых координат
		dotComListTest.get(0).setLocationCells(newLocations1);
		dotComListTest.get(1).setLocationCells(newLocations2);
		dotComListTest.get(2).setLocationCells(newLocations3);
		
		// Проверка интересующей функции
		for (String guess : input1) {
			if (dotComBust.checkUserGuess(guess) == false) return false;
		}
		
		for (String guess : input2) {
			if (dotComBust.checkUserGuess(guess) == false) return false;
		}
		
		for (String guess : input3) {
			if (dotComBust.checkUserGuess(guess) == false) return false;
		}
		
		return true;
	}
	
	private void showCells() {
		System.out.println("Out of all cells of all dots");
		for (DotCom dot : dotComList) {
			String name = dot.getName();
			System.out.print("\t" + name + ": ");
			for (String cell : dot.getLocationCells()) {
				System.out.print(cell + "  ");
			}
			System.out.print("\n");
		}
	}
	
}