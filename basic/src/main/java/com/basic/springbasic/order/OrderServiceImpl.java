package com.basic.springbasic.order;

import com.basic.springbasic.discount.DiscountPolicy;
import com.basic.springbasic.discount.FixDiscountPolicy;
import com.basic.springbasic.member.Member;
import com.basic.springbasic.member.MemberRepository;
import com.basic.springbasic.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //생성자랑 똑같이 만들어준다. => final로 된거
public class OrderServiceImpl implements OrderService{
    //회원 찾을 거
    private final MemberRepository memberRepository; //왜 private final로 들어가지?

    //고정 할인 정책 => 여기서 정률 할인 정책으로 바꾸려고 했었으면, private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); 이런식으로 선언해줬어야 한다.
    private final DiscountPolicy discountPolicy; // 이 부분이 DIP를 지키는 부분 => 인터페이스에만 의존 시키게끔 코드를 바꿨다.


//    @Autowired(required = false)
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    } //여기서는 final 빼야함 -> 선택, 변경  가능성 있으므로
//    @Autowired(required = false)
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy rateDiscountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = rateDiscountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);

    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
