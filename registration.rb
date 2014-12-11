require 'HTTParty'

class Registration

  def register
    info = HTTParty.post( "http://challenge.code2040.org/api/register", 
                           :body => { "email"=> "ifkalu@yahoo.com", "github"=> "https://github.com/ikalu/code2040Challenge.git"}.to_json, 
                           :headers => { 'Content-Type' => 'application/json' } )
    print(info["result"])
  end

  def print(someThing)
    puts someThing
  end

end

ifeanyi = Registration.new
ifeanyi.register
