package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@Transactional
@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
        //Given
        List<TrelloListDto> firstSetOfTrelloListsDto = new ArrayList<>();
        List<TrelloListDto> secondSetOfTrelloListDto = new ArrayList<>();

        firstSetOfTrelloListsDto.add(new TrelloListDto("001","testListOne", true));
        firstSetOfTrelloListsDto.add(new TrelloListDto("002","testListTwo", true));
        firstSetOfTrelloListsDto.add(new TrelloListDto("003", "testListThree", true));
        firstSetOfTrelloListsDto.add(new TrelloListDto("004", "testListFour", true));
        secondSetOfTrelloListDto.add(new TrelloListDto("007", "testListSeven", false));

        TrelloBoardDto firstTrelloBoardDto = new TrelloBoardDto("01", "boardOfListsClosed", firstSetOfTrelloListsDto);
        TrelloBoardDto secondTrelloBoadDto = new TrelloBoardDto("02", "boardOfListsOpen", secondSetOfTrelloListDto);

        List<TrelloBoardDto> listOfTrelloBoardsDto = new ArrayList<>();

        listOfTrelloBoardsDto.add(firstTrelloBoardDto);
        listOfTrelloBoardsDto.add(secondTrelloBoadDto);

        //When
        List<TrelloBoard> listsOfTrelloBoards = trelloMapper.mapToBoards(listOfTrelloBoardsDto);

        //Then
        assertEquals(2, listsOfTrelloBoards.size());
        assertEquals("01",listsOfTrelloBoards.get(0).getId());
        assertEquals("boardOfListsOpen", listsOfTrelloBoards.get(1).getName());
        assertFalse(listsOfTrelloBoards.get(1).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> firstSetOfTrelloLists = new ArrayList<>();
        List<TrelloList> secondSetOfTrelloList = new ArrayList<>();

        firstSetOfTrelloLists.add(new TrelloList("001","testListOne", true));
        firstSetOfTrelloLists.add(new TrelloList("002","testListTwo", true));
        firstSetOfTrelloLists.add(new TrelloList("003", "testListThree", true));
        firstSetOfTrelloLists.add(new TrelloList("004", "testListFour", true));
        secondSetOfTrelloList.add(new TrelloList("007", "testListSeven", false));

        TrelloBoard firstTrelloBoard = new TrelloBoard("01", "boardOfListsClosed", firstSetOfTrelloLists);
        TrelloBoard secondTrelloBoad = new TrelloBoard("02", "boardOfListsOpen", secondSetOfTrelloList);

        List<TrelloBoard> listOfTrelloBoards = new ArrayList<>();

        listOfTrelloBoards.add(firstTrelloBoard);
        listOfTrelloBoards.add(secondTrelloBoad);

        //When
        List<TrelloBoardDto> listsOfTrelloBoardsDto = trelloMapper.mapToBoardsDto(listOfTrelloBoards);

        //Then
        assertEquals(2, listsOfTrelloBoardsDto.size());
        assertEquals("01",listsOfTrelloBoardsDto.get(0).getId());
        assertEquals("boardOfListsOpen", listsOfTrelloBoardsDto.get(1).getName());
        assertFalse(listsOfTrelloBoardsDto.get(1).getLists().get(0).isClosed());
    }

    @Test
    public void testMapToList() {
        //Given
        List<TrelloListDto> setOfTrelloListsDto = new ArrayList<>();

        setOfTrelloListsDto.add(new TrelloListDto("001","testListOne", true));
        setOfTrelloListsDto.add(new TrelloListDto("002","testListTwo", true));
        setOfTrelloListsDto.add(new TrelloListDto("003", "testListThree", true));
        setOfTrelloListsDto.add(new TrelloListDto("004", "testListFour", false));

        //When
        List<TrelloList> setOfTrelloLists = trelloMapper.mapToList(setOfTrelloListsDto);

        //Then
        assertEquals(4, setOfTrelloLists.size());
        assertEquals("001", setOfTrelloLists.get(0).getId());
        assertEquals("testListTwo", setOfTrelloLists.get(1).getName());
        assertTrue(setOfTrelloLists.get(2).isClosed());
    }

    @Test
    public void testMapToListDto() {
        //Given
        List<TrelloList> setOfTrelloLists = new ArrayList<>();

        setOfTrelloLists.add(new TrelloList("001","testListOne", true));
        setOfTrelloLists.add(new TrelloList("002","testListTwo", true));
        setOfTrelloLists.add(new TrelloList("003", "testListThree", true));
        setOfTrelloLists.add(new TrelloList("004", "testListFour", false));

        //When
        List<TrelloListDto> setOfTrelloListsDto = trelloMapper.mapToListDto(setOfTrelloLists);

        //Then
        assertEquals(4, setOfTrelloListsDto.size());
        assertEquals("001", setOfTrelloListsDto.get(0).getId());
        assertEquals("testListTwo", setOfTrelloListsDto.get(1).getName());
        assertFalse(setOfTrelloLists.get(3).isClosed());
    }

    @Test
    public void testMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("testCard", "Card for testing", "top", "001");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("testCard", trelloCardDto.getName());
        assertEquals("Card for testing", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("001", trelloCardDto.getListId());
    }

    @Test
    public void testMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("testCardDto", "DtoCard for testing", "bottom", "002");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("testCardDto", trelloCard.getName());
        assertEquals("DtoCard for testing", trelloCard.getDescription());
        assertEquals("bottom", trelloCard.getPos());
        assertEquals("002", trelloCard.getListId());

    }
}
