package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    // basicItemController가 @Controller에 의해 스프링 빈에 등록이 되면서, 생성자로 itemRepository가 의존성이 주입이 된다.
    //생성자가 아래처럼 하나만 있으면 @Autowired를 쓰지 않아도 된다.
    //lombok: RequiredArgsContstructor => final을 가진 친구로 생성자를 만들어준다.


    ///basic/items로 들어가게 되면 ,Get방식으로 아래에 있는 items가 호출된다.
    //model에 items라는 컬렉션을 만든다.

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items"; //view 반환
    }


    /**
     * 테스트용 데이터 추가
     */
    @PostConstruct
    public void init() { //test용으로 값 넣어두기 위함
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemA", 20000, 20));
    }

    //상품상세용
    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        log.info("itemId={}", itemId);
        log.info("item={}", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() { // addForm으로 가는 뷰=> 그냥 뷰 템플릿만 호출한다.
        return "basic/addForm"; // html/addForm.html에 관련됨
    }

    @PostMapping("/add")
    public String addItemV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam Integer quantity,
            Model model
    ) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);
        model.addAttribute("item", item);

        return "basic/item";
    }

    //@RequestParam으로 변수를 하나하나 받아서 Item을 생성하는 것은 불편 => @ModelAttribute를 사용해서 한번에 처리
    @PostMapping("/add")
    public String addItemV2(
            @ModelAttribute("item") Item item, // item을 세팅해주고, Item객체를 하나 받을것을 가정하고 파라미터로 선언한다.
            Model model
    ) {
        //@ModelAttribute가 아래의 행위들을 해준다.
//        Item item = new Item();
//        item.setItemName(itemName);
//        item.setPrice(price);
//        item.setQuantity(quantity);

        itemRepository.save(item);
        // 심지어 model에다가 item을 자동으로 추가해준다.
//        model.addAttribute("item", item);

        return "basic/item";
    }
    //@ModelAttribute는 Item객체를 생성, 요청 파라미터의 값을 프로퍼티 접근법(setXxx)으로 입력해준다.

    @PostMapping("/add")
    public String addItemV3(@ModelAttribute Item item) { // ModelAttribute이름 생략
        itemRepository.save(item);
        return "basic/item";
    }

    //상품 수정
    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) { //itemId를 경로에서 받아서
        Item item = itemRepository.findById(itemId); // 받은 변수로 item을 itemRepository에서 찾아온다.
        model.addAttribute("item", item); // model에 저장 한뒤,
        return "basic/editForm"; // logic view를 반환해준다.
    }

    // 상품  수정 개발
    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) { //itemId를 경로에서 받아서
        itemRepository.update(itemId, item); // 새로 받은 item객체
        return "redirect:/basic/items/{itemId}"; // logic view를 반환해준다.
    }

    // 새로고침 => 다시 post됨 => 상품 저장 후에 뷰 템플릿으로 이동하는 것이 아니라, 상품 상세 화면으로 리다이렉트를 호출해주면 도니다.

    @PostMapping("/add")
    public String addItemV5(Item item) { //@ModelAttribute
        itemRepository.save(item);
        return "redirect:/basic/items" + item.getId(); //위와 같이 더해서 사용하는 것은 URL 인코딩이 안되기 때문에 위험하다.
        //RedirectAttribute를 사용하자.
    }

    @PostMapping("/add")
    public String addItemV6(Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
        //RedirectAttribute를 사용하자.
    }
}
