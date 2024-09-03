Проект Симуляция.

https://zhukovsd.github.io/java-backend-learning-course/projects/simulation/

## Работа над ошибками

### 1. Нейминг

> -Название должно обьяснять суть явления. Папка "objects" ничего не обьясняет- в яве все "обьекты".
> На самом деле в этой папке хранятся существа, которые не ходят. Это нужно отразить в названии папки.

*Поменял название папки на ***environment****


> -Папка algoritms. Все в исходном тексте компьютерной программы это алгоритмы. В этой папке хранятся стратегии поиска.

*Поменял название папки на ***search****

### 2. Никогда не возвращай null

```java

public Entity getEntity() {
    return entity;  //может вернуть null
}
//...

```

*Упростил класс ***Cell***, хранением ***Entity*** теперь занимается только ***HashMap*** в классе
***GridMap***. Возвращает ***Optional*** - везде при вызове ***getEntity()*** в дальнейшем - проверяю
Optional*

```java
public Optional<Entity> getEntity(Cell cell) {
    return Optional.ofNullable(cells.get(cell));
}
```

### 3. class Cell, ячейка для карты, функциональный аналог Координаты.

> -Координата, которая не координата- хранит не только положение сущности на плоскости(x, y), но и саму сущность.
> Получилось что-то вроде Map.Entry, только обвешенный дополнительными ответственностями.
> Разобраться что это такое и почему там есть некоторые методы(generateConnectedGridGraph() и т.д.), трудно.
> Я до конца понять так и не смог.
> Прошу высказаться по этому поводу других читателей- кажется ли вам класс Cell простым и понятным, можете ли вы полностью
> его понять?

> -Из-за того, что Cell является базовым классом для хранения соотношения сущность-координата, все другие классы
> подстраиваются под Cell.
> И их принцип работы тоже становится непонятным и неочевидным.

*Класс ***Cell*** изначально представлял собой своего рода вершину связного графа
он хранил в себе ссылки на ***Cell*** клетки соседей. Так же мог содержать в себе ***Entity***.
Сделано это было изначально для удобства дальнейшей работы с алгоритмами. Мне показалось, что так будет более удобно с
любой клетки
иметь возможность пройти путь до любой другой точки. После ревью заметил, что фишка с обходом с любой клетки применяется
только в ***BFSSearch***, а в других местах
такая организация усложняет доступ к ***Entity****

*Упростил класс до записи*

```java
public record Cell(int x, int y) {
}
```

### 4. class GridMap, Карта.

> -Вообще, обычно игровая карта это очень простая сущность.
> Карта хранит соотношение существо+координата и фактически является оберткой над хэшмапой или массивом.
> Где в хешмапе связь существо+координата реализуется в виде именно такой связи, а в массиве в роли координаты выступает
> номер ячейки массива.
> Здесь же Карта хранит лист Cell'ов- трудных для понимания сущностей.
> Поэтому потенциально простой класс Карты здесь тоже становится трудным для пониманием классом.

*Упростил ***GridMap*** - сделал хранение пар Cell/Entity через ***HashMap****

### 5. Поиск пути

> -Концепция поиска пути мне не ясна. По идее, поиск пути должен вернуть путь от точки А до точки Б. Путь это
> последовательность точек, которая может быть представлена через список, очередь или ноду связанного списка.
> Здесь результатом поиска является Cell
> public interface SearchStrategy {
> Cell find(Cell startCell, Class<? extends Consumable> whatToFind);
> }
> Может быть, Cell еще выполняет роль связанного списка? Да вроде нет.

*Результат поиска - ***Cell*** - метод просчитывает путь до ближайшей цели и возвращает
первую клетку из этого пути.*

Существа в моей реализации двигаются по одной клетке. Сделано это по двум причинам:*

1. Для более плавной отрисовки движений существ
2. для большей "честности" по отношению к существам.
   В процессе выполнения ***MoveAction*** все существа двигаются по одной клетке за шаг (количество шагов просчитывается
   по скоростям существ).
   В результате цель становится добычей именно ближайшего к ней охотника, а не охотника, у которого был вызван метод
   ***move()*** первым.
   Изначальное название выбрал неудачно.

*Переименовал метод, теперь название выражает то, что он делает на самом деле*

```java
Cell findNextCellToTarget(Cell startCell, GridMap map, Class<? extends Entity> whatToFind);
```

### 6. class Herbivore

> -Нарушение SRP, создающий метод, который знает часть логики чужой ответственности- что приложению нужно создавать
> существо со случайными параметрами в рамках заданного диапазона.
> Заниматься этим должен клиентский код, тем более у тебя есть специальные классы для создания существ
> public static Herbivore randomHerbivore() {
> Random random = new Random();
> int randomHealth = random.nextInt(MINIMUM_HEALTH,MAXIMUM_HEALTH);
> int randomSpeed = random.nextInt(MINIMUM_SPEED,MAXIMUM_SPEED);
> return new Herbivore(randomHealth, randomSpeed);
> }

*В ***Herbivore*** и ***Predator*** создал статические методы для создания существа с заданными параметрами*

```java
public static Predator newInstance(int health, int speed, int attack) {
    return new Predator(health, speed, attack);
}
```

*Метод по созданию рандомных существ вынес в клиентский код, где существа и появляются - в ***SpawnAction****

```java
public static Predator randomPredator() {
    Random rand = new Random();
    return Predator.newInstance(rand.nextInt(Predator.MIN_HEALTH, Predator.MAX_HEALTH),
            rand.nextInt(Predator.MIN_SPEED, Predator.MAX_SPEED),
            rand.nextInt(Predator.MIN_ATTACK, Predator.MAX_ATTACK));
}
```

### 7. abstract class Action

> -Cell randomEmptyCell(GridMap map) и List<Creature> allCreaturesFromMap(GridMap map) не являются общим поведением для
> всех наследников

*Вынес вспомогательные ***static*** методы для ***Action*** в отдельный класс ***ActionUtils****

### 8. Классы -SpawnAction

> -Куча классов которые делают одно и то же- населяют карту существами определенного вида. Можно сделать один
> универсальный.

*Сделал один единственный класс с вложенным ***enum EntityType****

```java
public enum EntityType {
    ROCK(0.09, Rock.class, Rock::new),
    GRASS(0.06, Grass.class, Grass::new),
    TREE(0.08, Tree.class, Tree::new),
    PREDATOR(0.06, Predator.class, SpawnAction::randomPredator),
    HERBIVORE(0.06, Herbivore.class, SpawnAction::randomHerbivore);

    private final double spawnRate;
    private final Class<? extends Entity> entityClass;
    private final Supplier<Entity> creator;

    EntityType(double spawnRate, Class<? extends Entity> entityClass, Supplier<Entity> creator) {
        this.spawnRate = spawnRate;
        this.entityClass = entityClass;
        this.creator = creator;
    }

    private Entity newInstance() {
        return creator.get();
    }

    private double spawnRate() {
        return spawnRate;
    }

    private Class<? extends Entity> entityClass() {
        return entityClass;
    }

}
```

### 9. class Main, содержит точку входа main

> -Плохо то, что часть инициализации SimulationController происходит в самом SimulationController через

> -Идеологическая ошибка- юзер должен выбирать не рендерер, юзер должен выбирать UI.
> Дело не только в названии- это влечет за собой неправильное инициализирование контроллера симуляции в контроллере
> симуляции.
> Потому что от UI зависит не только рендерер, но и контроллер симуляции.


*Теперь в ***Main*** выбирается ***UI*** и ***SimulationController*** создается статическим методом по значению UI*

```java
System.out.println(ConsoleInput.ASK_FOR_UI);

SimulationController.UI ui = ConsoleInput.chooseUi();
SimulationController controller = SimulationController.createFromUI(ui, map);
```

### 10. class SimulationController

> -Находится в папке view. Если в проекте есть контроллеры и вью, то контроллер не может быть вью и находиться в папке с
> таким названием.

*Переместил в папку game/controller*

> -Нарушение SRP, OCP, знает о своих потомках, создает и запускает их через статический запускатор. Получается какой-то
> гибрид создающего метода и запускатора

> -Все потомки (SwingSimulationController, ConsoleSimulationController, КинопроекторSimulationController) должны иметь
> единый интерфейс(не в смысле "единый interface", а в смысле "единый способ") пуска- т.е. общий метод пуск()/start()
> /поехали(),
> что бы их можно было запускать одинаково, а не как сейчас
> SimulationController swingController = new SwingSimulationController(sim);
> new Thread(swingController).start();

*Статический создаватор/запускатор убрал. Сделал отдельный метод по созданию
SimulationController в соответствии с UI. Добавил метод startSimulation для запуска*

```java
SimulationController controller = SimulationController.createFromUI(ui, map);
controller.startSimulation();
```

### 11. class SwingCell extends JPanel и class SwingMapRenderer extends JPanel implements MapRenderer, рендерят карту в окно через Свинг.

> -Распределение ответственностей мне не нравится. Очевидно, что хранить спрайты существ и т.д. должен Рендерер, а не
> Ячейка.

*Переместил хранение спрайтов существ в SwingMapRenderer*

### 12. class ConsoleMapRenderer implements MapRenderer

> -Не ясно, какой именно цвет соответствует атаке, лечению и т.д. При необходимости будет труднее поменять
> public final class ConsoleMapRenderer implements MapRenderer {
> private static final String ATTACK_COLOR = "\u001B[31m";
> private static final String HEALTH_COLOR = "\u001B[32m";
> private static final String RESET_COLOR = "\u001B[0m";
> //...
> }

*Вынес ANSII коды в отдельный enum*

```java
private enum ANSIIColor {
    DEFAULT("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m");

    private final String code;

    ANSIIColor(String code){ 
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
```
