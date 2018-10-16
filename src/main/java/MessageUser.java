public class MessageUser {
    private Long chatID;
    private String messageID;

    public MessageUser(Long chatID, String messageID) {
        this.chatID = chatID;
        this.messageID = messageID;
    }

    public void setChatID(Long chatID) {
        this.chatID = chatID;
    }

    public String getReadyMessage() {
        return chatID + messageID;
    }

    public void setMessageID(String messageID) {
        if(messageID.startsWith("magnet:?xt=")) {
            this.messageID = messageID;
        }
    }
}
